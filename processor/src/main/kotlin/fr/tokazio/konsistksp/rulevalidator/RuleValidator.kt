package fr.tokazio.konsistksp.rulevalidator

import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.KonsistKspKoAssertionFailedException
import fr.tokazio.konsistksp.KonsistKspScopeCreator
import fr.tokazio.konsistksp.api.Annotated
import fr.tokazio.konsistksp.api.Logger
import fr.tokazio.konsistksp.api.RuleProcessor
import fr.tokazio.konsistksp.api.SymbolResolver
import fr.tokazio.konsistksp.konsist.KonsistKspKoClassDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoFileDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoImportDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoScopeCreator
import fr.tokazio.konsistksp.kotlin.KotlinCompiler
import fr.tokazio.konsistksp.resolver.KonsistKspNode
import java.io.File
import java.lang.reflect.InvocationTargetException

class RuleValidator(
    private val logger: Logger,
    options: Map<String, String>,
) : RuleProcessor {
    private lateinit var konsistScopeCreator: KonsistKspKoScopeCreator

    private val projectBase: File = File(options["konsistKspProjectBase"]!!)

    private val kotlinCompiler = KotlinCompiler(projectBase, logger, options)

    private val ruleFinders =
        listOf(
            ClasspathRuleFinder(
                logger = logger,
                classpath = options["konsistKspClasspath"]?.split(":") ?: emptyList(),
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
                            logger.debug("rule class $clazz containing ${functionNames.size} function @ArchitectureRule")
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
                            logger.error("Invoking $f caused exception ${ex::class.java.name}: ${ex.message}")
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
                            logger.error("Invoking $f caused exception ${ex::class.java.name}: ${ex.message}")
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
