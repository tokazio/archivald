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
import fr.tokazio.konsistksp.konsist.KonsistKspKoClassDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoFileDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoImportDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoScopeCreator
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_CLASSPATH_OPTION
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
    options: Map<String, String>,
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
                    logger.info("applying rule $functionName (from ${instance::class.qualifiedName}) (no base package) ...")
                    try {
                        f.invoke(instance, konsistScopeCreator, "")
                        logger.debug("successfully applied rule $functionName from ${instance::class.qualifiedName}")
                    } catch (ex: InvocationTargetException) {
                        if (ex.cause is KonsistKspKoAssertionFailedException) {
                            handleAssertionException(ex.cause as KonsistKspKoAssertionFailedException)
                        } else if (ex.targetException is KoAssertionFailedException) {
                            throw IllegalStateException(
                                "This rule is using com.lemonappdev.konsist.api.verify.assertTrue or import com.lemonappdev.konsist.api.verify.assertFalse instead of fr.tokazio.konsistksp.assertTrue or fr.tokazio.konsistksp.assertFalse",
                                ex.targetException,
                            )
                        } else {
                            logger.error("Invoking $f caused exception ${ex::class.java.name}: ${ex.message}\n${ex.stackTraceToString()}")
                            throw ex
                        }
                    }
                } catch (ex: NoSuchMethodException) {
                    val f = instance.javaClass.getMethod(functionName, KonsistKspScopeCreator::class.java)
                    logger.info("applying rule $functionName (from ${instance::class.qualifiedName}) ...")
                    try {
                        f.invoke(instance, konsistScopeCreator)
                        logger.debug("successfully applied rule $functionName from ${instance::class.qualifiedName}")
                    } catch (ex: InvocationTargetException) {
                        if (ex.cause is KonsistKspKoAssertionFailedException) {
                            handleAssertionException(ex.cause as KonsistKspKoAssertionFailedException)
                        } else if (ex.targetException is KoAssertionFailedException) {
                            throw IllegalStateException(
                                "This rule is using com.lemonappdev.konsist.api.verify.assertTrue or import com.lemonappdev.konsist.api.verify.assertFalse instead of fr.tokazio.konsistksp.assertTrue or fr.tokazio.konsistksp.assertFalse",
                                ex.targetException,
                            )
                        } else {
                            logger.error("Invoking $f caused exception ${ex::class.java.name}: ${ex.message}\n${ex.stackTraceToString()}")
                            throw ex
                        }
                    }
                }
            }
        }
        return emptyList()
    }

    private fun handleAssertionException(ex: KonsistKspKoAssertionFailedException) {
        ex.failedItems.forEach {
            logger.debug("rule failed at ${it::class.java.name} level")
            when (it) {
                is KonsistKspKoClassDeclaration -> logger.error("${ex.testName} failed", it.inner.containingFile)
                is KonsistKspKoFileDeclaration -> logger.error("${ex.testName} failed", it.file)
                is KonsistKspKoImportDeclaration -> {
                    val message =
                        "${ex.testName} but found 'import ${it.importString}' at file://${it.location}:${it.konsistKspImport.location.lineNumber}"
                    logger.error(message, KonsistKspNode(it.konsistKspImport))
                }

                else -> logger.error("Validation error unknown: ${it::class.java}")
            }
        }
    }
}
