package fr.tokazio.konsistksp.rulevalidator

import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.KonsistKspKoAssertionFailedException
import fr.tokazio.konsistksp.KonsistKspScopeCreator
import fr.tokazio.konsistksp.internal.RuleProcessor
import fr.tokazio.konsistksp.internal.SymbolResolver
import fr.tokazio.konsistksp.internal.kotlin.KotlinCompiler
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.Annotated
import fr.tokazio.konsistksp.internal.model.Node
import fr.tokazio.konsistksp.konsist.*
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_CLASSPATH_OPTION
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_FAILFAST_OPTION
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_LOG_SUCCESS_OPTION
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_PROJECT_BASE_OPTION
import fr.tokazio.konsistksp.ksp.bridge.model.KonsistKspNode
import fr.tokazio.konsistksp.rulevalidator.finder.ClasspathRuleFinder
import fr.tokazio.konsistksp.rulevalidator.finder.SrcRuleFinder
import java.io.File
import java.lang.reflect.InvocationTargetException

// Should not use com.google.devtools.ksp
// Should not use com.lemonappdev.konsist

/**
 * Validate the compiling files against a set of `@ArchitectureRule` collected from:
 * * src/rules of the same project
 * * META-INF/`TARGET_RULE_FILE` of some jars from the classpath (given as the processor option `KONSIST_KSP_CLASSPATH_OPTION`)
 */
class RuleValidator(
    private val logger: Logger,
    private val options: Map<String, String>,
) : RuleProcessor {
    private lateinit var konsistScopeCreator: KonsistKspKoScopeCreator

    private val projectBase: File = File(options[KONSIST_KSP_PROJECT_BASE_OPTION]!!)

    private val kotlinCompiler = KotlinCompiler(projectBase, logger, options)

    private val ruleFinders =
        listOf(
            ClasspathRuleFinder(
                logger = logger,
                classpath = options[KONSIST_KSP_CLASSPATH_OPTION]?.split(":") ?: emptyList(),
            ),
            SrcRuleFinder(
                logger = logger,
                projectBase = projectBase,
                kotlinCompiler = kotlinCompiler,
            ),
        )

    private val compiledClassFiles: MutableSet<String> = mutableSetOf()

    init {
        logger.debug("detected base project folder as ${projectBase.absolutePath}")
        ruleFinders.forEach {
            compiledClassFiles.addAll(it.find())
        }
    }

    override fun process(resolver: SymbolResolver): List<Annotated> {
        logger.info("Starting rules validation...")
        if (options[KONSIST_KSP_LOG_SUCCESS_OPTION] == "false") {
            logger.info("Success will not be shown, $KONSIST_KSP_LOG_SUCCESS_OPTION")
        }
        if (options[KONSIST_KSP_FAILFAST_OPTION] == "false") {
            logger.info("Fail fast is off, $KONSIST_KSP_FAILFAST_OPTION")
        }
        konsistScopeCreator =
            KonsistKspKoScopeCreator(
                logger,
                resolver,
            )

        val instances =
            compiledClassFiles
                .mapNotNull { classFile ->
                    try {
                        val clazz = Thread.currentThread().contextClassLoader.loadClass(classFile)
                        if (clazz == null) {
                            logger.warn("can't load rule class $classFile")
                            null
                        } else {
                            logger.debug("loaded rule class $clazz")
                            val functionNames =
                                clazz.methods
                                    .filter { ksFunction ->
                                        ksFunction.annotations.any { annotation ->
                                            annotation
                                                .toString()
                                                .contains(ArchitectureRule::class.qualifiedName.toString())
                                        }
                                    }.map {
                                        it.name
                                    }
                            logger.debug(
                                "rule class $clazz containing ${functionNames.size} function @${ArchitectureRule::class.java.simpleName}",
                            )
                            if (functionNames.isNotEmpty()) {
                                val instance = clazz.getDeclaredConstructor().newInstance()
                                instance to functionNames
                            } else {
                                null
                            }
                        }
                    } catch (_: NoClassDefFoundError) {
                        logger.warn("rule class loading failed $classFile")
                        null
                    }
                }.toMap()

        instances.forEach { (instance, functions) ->
            logger.debug("applying ${functions.size} rule(s) from ${instance::class.qualifiedName}")
            functions.forEach { functionName ->
                try {
                    val f =
                        instance.javaClass.getMethod(
                            functionName,
                            KonsistKspScopeCreator::class.java,
                            String::class.java,
                        )
                    val architectureRuleAnnotation = f.annotations.first() as ArchitectureRule
                    val ruleDescription = architectureRuleAnnotation.value.ifEmpty { functionName }
                    logger.debug("applying rule '$ruleDescription' (from ${instance::class.qualifiedName}) (no base package) ...")
                    try {
                        f.invoke(instance, konsistScopeCreator, "")
                        success(ruleDescription, instance)
                    } catch (ex: InvocationTargetException) {
                        if (ex.cause is KonsistKspKoAssertionFailedException) {
                            handleAssertionException(ruleDescription, ex.cause as KonsistKspKoAssertionFailedException)
                        } else if (ex.targetException is KoAssertionFailedException) {
                            throw IllegalStateException(
                                "⛔ This rule is using com.lemonappdev.konsist.api.verify.assertTrue or import com.lemonappdev.konsist.api.verify.assertFalse instead of fr.tokazio.konsistksp.assertTrue or fr.tokazio.konsistksp.assertFalse",
                                ex.targetException,
                            )
                        } else {
                            logger.error(
                                "⛔ Invoking '$f' caused exception '${ex::class.java.name}': ${ex.message}\n${ex.stackTraceToString()}",
                            )
                            throw ex
                        }
                    }
                } catch (_: NoSuchMethodException) {
                    val f = instance.javaClass.getMethod(functionName, KonsistKspScopeCreator::class.java)
                    val architectureRuleAnnotation = f.annotations.first() as ArchitectureRule
                    val ruleDescription = architectureRuleAnnotation.value.ifEmpty { functionName }
                    logger.debug("applying rule '$ruleDescription' (from ${instance::class.qualifiedName}) ...")
                    try {
                        f.invoke(instance, konsistScopeCreator)
                        success(ruleDescription, instance)
                    } catch (ex: InvocationTargetException) {
                        if (ex.cause is KonsistKspKoAssertionFailedException) {
                            handleAssertionException(ruleDescription, ex.cause as KonsistKspKoAssertionFailedException)
                        } else if (ex.targetException is KoAssertionFailedException) {
                            throw IllegalStateException(
                                "⛔ This rule is using com.lemonappdev.konsist.api.verify.assertTrue or import com.lemonappdev.konsist.api.verify.assertFalse instead of fr.tokazio.konsistksp.assertTrue or fr.tokazio.konsistksp.assertFalse",
                                ex.targetException,
                            )
                        } else {
                            logger.error(
                                "⛔ Invoking $f caused exception ${ex::class.java.name}: ${ex.message}\n${ex.stackTraceToString()}, run your build command with --stacktrace to get more information",
                            )
                            throw ex
                        }
                    }
                }
            }
        }
        if (!errors.isEmpty()) {
            logger.warn("❌ ${errors.size} failure(s)")
            errors.forEach {
                failureWarn(it.first, it.second)
            }
            logger.error("")
        }
        return emptyList()
    }

    private fun handleAssertionException(
        failureMessage: String,
        ex: KonsistKspKoAssertionFailedException,
    ) {
        ex.failedItems.forEach {
            logger.debug("X rule '$failureMessage' failed (at '${it::class.java.name}' level)")

            when (it) {
                is KonsistKspKoClassDeclaration ->
                    fail(
                        "'$failureMessage' failed at file://${it.location}:${it.classDeclaration.location.lineNumber}",
                        it.classDeclaration.containingFile,
                    )

                is KonsistKspKoFileDeclaration -> fail("'$failureMessage' failed at file://${it.path}:1", it.file)
                is KonsistKspKoImportDeclaration -> {
                    val message =
                        "'$failureMessage' but found 'import ${it.importString}' at file://${it.location}:${it.konsistKspImport.location.lineNumber}"
                    fail(message, KonsistKspNode(it.konsistKspImport))
                }
                is KonsistKspKoObjectDeclaration -> {
                    fail(
                        "'$failureMessage' failed at file://${it.location}:${it.classDeclaration.location.lineNumber}",
                        it.classDeclaration,
                    )
                }

                else ->
                    fail(
                        "Validation error unknown: ${it::class.java}, you should handle it in ${this::class.java.simpleName}.handleAssertionException",
                        null,
                    )
            }
        }
    }

    private fun fail(
        message: String,
        node: Node? = null,
    ) {
        if (options[KONSIST_KSP_FAILFAST_OPTION] == "true") {
            logger.error("❌ $message", node)
        } else {
            errors.add(message to node)
        }
    }

    private fun failureWarn(
        message: String,
        node: Node? = null,
    ) {
        logger.warn("❌ $message", node)
    }

    private val errors = mutableListOf<Pair<String, Node?>>()

    private fun success(
        ruleDescription: String,
        instance: Any,
    ) {
        if (options[KONSIST_KSP_LOG_SUCCESS_OPTION] == "true") {
            logger.info("✅ successfully applied rule '$ruleDescription' from ${instance::class.qualifiedName}")
        }
    }
}
