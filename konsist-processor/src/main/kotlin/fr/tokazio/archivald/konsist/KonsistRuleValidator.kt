package fr.tokazio.archivald.konsist

import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import fr.tokazio.archivald.ArchitectureRule
import fr.tokazio.archivald.internal.*
import fr.tokazio.archivald.internal.kotlin.KotlinCompiler
import fr.tokazio.archivald.internal.logger.Logger
import fr.tokazio.archivald.internal.model.Annotated
import fr.tokazio.archivald.internal.model.Node
import fr.tokazio.archivald.konsist.bridge.*
import fr.tokazio.archivald.rulevalidator.finder.ClasspathRuleFinder
import fr.tokazio.archivald.rulevalidator.finder.SrcRuleFinder
import java.io.File
import java.lang.reflect.InvocationTargetException

/**
 * Validate the compiling files against a set of `@ArchitectureRule` collected from:
 * * src/rules of the same project
 * * META-INF/`TARGET_RULE_FILE` of some jars from the classpath (given as the processor option `KONSIST_KSP_CLASSPATH_OPTION`)
 */
class KonsistRuleValidator(
    private val logger: Logger,
    private val options: Map<String, String>,
) : RuleProcessor {
    private val projectBase: File = File(options[ARCHIVALD_PROJECT_BASE_OPTION]!!)
    private val kotlinCompiler = KotlinCompiler(projectBase, logger, options)
    private val errors = mutableListOf<Pair<String, Node?>>()
    private val ruleFinders =
        listOf(
            ClasspathRuleFinder(
                logger = logger,
                classpath = options[ARCHIVALD_CLASSPATH_OPTION]?.split(":") ?: emptyList(),
            ),
            SrcRuleFinder(
                logger = logger,
                projectBase = projectBase,
                kotlinCompiler = kotlinCompiler,
            ),
        )
    private val compiledClassFiles: Set<String> =
        ruleFinders.flatMapTo(mutableSetOf()) {
            it.find()
        }

    init {
        logger.debug("detected base project folder as ${projectBase.absolutePath}")
    }

    override fun process(resolver: SymbolResolver): List<Annotated> {
        logger.info("Starting rules validation...")
        displayValidationOptions()
        val konsistScopeCreator =
            KonsistKspKoScopeCreator(
                logger,
                resolver,
            )
        val rules: List<Rule> =
            compiledClassFiles
                .flatMap { classFilename ->
                    loadRuleClass(classFilename)?.let { clazz ->
                        instantiateRuleClass(clazz)?.let { targetInstance ->
                            loadRuleMethods(clazz).map { functionName ->
                                Rule(targetInstance, functionName)
                            }
                        }
                    } ?: emptyList()
                }

        logger.debug("applying ${rules.size} rule(s)")
        rules
            .sortedBy {
                it.name
            }.forEach { rule ->
                validateRule(rule, konsistScopeCreator)
            }
        if (!errors.isEmpty()) {
            logger.warn("❌ ${errors.size} failure(s)")
            errors.forEach {
                logFailureWithWarn(it.first, it.second)
            }
            if (options[ARCHIVALD_IGNORE_FAILURES_OPTION] == "false") {
                logger.error("Build failed")
            }
        }
        return emptyList()
    }

    private fun instantiateRuleClass(clazz: Class<*>): Any? =
        try {
            clazz.getDeclaredConstructor().newInstance()
        } catch (_: NoSuchMethodException) {
            // Can't instantiate the class ?!
            null
        }

    private fun loadRuleMethods(clazz: Class<*>): List<String> =
        clazz.methods
            .filter { ksFunction ->
                ksFunction.annotations.any { annotation ->
                    annotation
                        .toString()
                        .contains(ArchitectureRule::class.qualifiedName.toString())
                }
            }.map {
                it.name
            }.also {
                logger.debug(
                    "rule class $clazz containing ${it.size} function @${ArchitectureRule::class.java.simpleName}",
                )
            }

    private fun loadRuleClass(classFilename: String): Class<*>? =
        try {
            val clazz = Thread.currentThread().contextClassLoader.loadClass(classFilename)
            if (clazz == null) {
                logger.warn("can't load rule class $classFilename")
                null
            } else {
                logger.debug("loaded rule class $clazz")
                clazz
            }
        } catch (_: NoClassDefFoundError) {
            logger.warn("rule class loading failed $classFilename")
            null
        }

    private fun validateRule(
        rule: Rule,
        konsistScopeCreator: KonsistKspKoScopeCreator,
    ) {
        logger.debug("applying rule '${rule.description}' (from ${rule.className})")
        try {
            runRule(rule, konsistScopeCreator)
        } catch (ex: InvocationTargetException) {
            logger.error(
                "⛔ Invoking '${rule.methodName}' caused exception '${ex::class.java.name}': ${ex.message}\n${ex.stackTraceToString()}\nRebuild using --stacktrace to get ore details",
            )
        }
    }

    private fun runRule(
        rule: Rule,
        konsistScopeCreator: KonsistKspKoScopeCreator,
    ) = try {
        AssertionResults.clear()
        if (rule.method.parameterCount == 2) {
            rule.method.invoke(rule.targetInstance, konsistScopeCreator, "")
        } else {
            rule.method.invoke(rule.targetInstance, konsistScopeCreator)
        }
        if (AssertionResults.results.isNotEmpty()) {
            throw InvocationTargetException(
                AssertionResults.asOneException(),
            )
        }
        logSuccess(rule)
    } catch (ex: InvocationTargetException) {
        if (ex.cause is KonsistKspKoAssertionFailedException) {
            handleAssertionException(rule.description, ex.cause as KonsistKspKoAssertionFailedException)
        } else if (ex.targetException is KoAssertionFailedException) {
            throw IllegalStateException(
                "⛔'${rule.description}' (${rule.name}) is using com.lemonappdev.konsist.api.verify.assertTrue or import com.lemonappdev.konsist.api.verify.assertFalse instead of fr.tokazio.konsistksp.assertTrue or fr.tokazio.konsistksp.assertFalse",
                ex.targetException,
            )
        } else {
            throw ex
        }
    }

    private fun handleAssertionException(
        failureMessage: String,
        ex: KonsistKspKoAssertionFailedException,
    ) {
        ex.failedItems.forEach {
            logger.debug("rule '$failureMessage' failed (at '${it::class.java.name}' level)")
            when (it) {
                is KonsistKspKoClassDeclaration ->
                    logFailureWithError(
                        "'$failureMessage' on class ${it.name}  failed at file://${it.location}:${it.classDeclaration.location.lineNumber}",
                        it.classDeclaration.containingFile,
                    )

                is KonsistKspKoFileDeclaration ->
                    logFailureWithError(
                        "'$failureMessage' failed at file://${it.path}:1",
                        it.file,
                    )

                is KonsistKspKoPackageDeclaration ->
                    logFailureWithError(
                        "'$failureMessage' on package ${it.name}  failed at file://${it.path}:1",
                        it.classDeclaration,
                    )

                /*
                is KonsistKspKoImportDeclaration -> {
                    val message =
                        "'$failureMessage' but found 'import ${it.importString}' at file://${it.location}:${it.konsistKspImport.location.lineNumber}"
                    logFailureWithError(message, KonsistKspNode(it.konsistKspImport))
                }

                 */

                is KonsistKspKoObjectDeclaration -> {
                    logFailureWithError(
                        "'$failureMessage' on object ${it.name}  failed at file://${it.location}:${it.classDeclaration.location.lineNumber}",
                        it.classDeclaration,
                    )
                }

                is KonsistKspKoPropertyDeclaration -> {
                    logFailureWithError(
                        "'$failureMessage' on property ${it.name}  failed at file://${it.location}:${it.propertyDeclaration.location.lineNumber}",
                        it.propertyDeclaration,
                    )
                }

                is KonsistKspKoParameterDeclaration -> {
                    logFailureWithError(
                        "'$failureMessage' on parameter ${it.name} failed at file://${it.location}:${it.valueParameter.location.lineNumber}",
                        it.valueParameter,
                    )
                }

                else ->
                    logFailureWithError(
                        "Validation error unknown: ${it::class.java}, you should handle it in ${this::class.java.simpleName}.handleAssertionException",
                        null,
                    )
            }
        }
    }

    /**
     * Logging an error break the compilation
     */
    private fun logFailureWithError(
        message: String,
        node: Node? = null,
    ) {
        if (options[ARCHIVALD_FAILFAST_OPTION] == "true") {
            if (options[ARCHIVALD_IGNORE_FAILURES_OPTION] == "false") {
                logger.error("❌ $message", node)
            } else {
                logger.warn("❌ $message", node)
            }
        } else {
            errors.add(message to node)
        }
    }

    private fun logFailureWithWarn(
        message: String,
        node: Node? = null,
    ) {
        logger.warn("❌ $message", node)
    }

    private fun logSuccess(rule: Rule) {
        if (options[ARCHIVALD_LOG_SUCCESS_OPTION] == "true") {
            logger.info("✅ successfully applied rule '${rule.description}' from ${rule.className}")
        }
    }

    private fun displayValidationOptions() {
        if (options[ARCHIVALD_LOG_SUCCESS_OPTION] == "false") {
            logger.info("Success will not be shown, ${ARCHIVALD_LOG_SUCCESS_OPTION}")
        }
        if (options[ARCHIVALD_FAILFAST_OPTION] == "false") {
            logger.info("Fail fast is off, ${ARCHIVALD_FAILFAST_OPTION}")
        }
    }
}
