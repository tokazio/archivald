package fr.tokazio.konsistksp.rulevalidator

import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.KonsistKspKoAssertionFailedException
import fr.tokazio.konsistksp.KonsistKspScopeCreator
import fr.tokazio.konsistksp.api.Annotated
import fr.tokazio.konsistksp.api.Logger
import fr.tokazio.konsistksp.api.RuleProcessor
import fr.tokazio.konsistksp.api.SymbolResolver
import fr.tokazio.konsistksp.collector.CollectorEngine
import fr.tokazio.konsistksp.konsist.KonsistKspKoFileDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoImportDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoScopeCreator
import fr.tokazio.konsistksp.kotlin.KotlinCompiler
import java.io.File
import java.lang.reflect.InvocationTargetException

class RuleValidator(
    private val logger: Logger,
    options: Map<String, String>,
) : RuleProcessor {
    private lateinit var konsistScopeCreator: KonsistKspKoScopeCreator

    private val projectBase: File = File(options["konsistKspProjectBase"]!!)

    private val kotlinCompiler = KotlinCompiler(projectBase, logger, options)

    private val classpathRuleFinder =
        ClasspathRuleFinder(
            logger = logger,
            classpath = options["konsistKspClasspath"]?.split(":") ?: emptyList(),
        )

    private val srcRuleFinder =
        SrcRuleFinder(
            logger = logger,
            projectBase = projectBase,
            kotlinCompiler = kotlinCompiler,
        )

    init {
        logger.debug("detected base project folder as ${projectBase.absolutePath}")
        // TODO how to give ksp the different folder where to find the rules ?
        /* If we want to use already compiled rules from another jar
        val inFolder = "${projectBase.absolutePath}"
        logger.info("searching (class) rules from $inFolder")
        ruleClassFiles = CollectorEngine()
          .dirFilter {
            !it.contains("tmp/kapt3/stubs")
          }.fileFilter {
            it.endsWith(".class")
          }.collect(inFolder)
          .toSet()
        // TODO filter class containing @ArchitectureRule
        logger.info("found ${ruleClassFiles.size} (class) rules in $inFolder")
         */
        classpathRuleFinder.find()
        srcRuleFinder.find()
    }

    override fun process(resolver: SymbolResolver): List<Annotated> {
        logger.info("analysis...")
        konsistScopeCreator =
            KonsistKspKoScopeCreator(
                logger,
                resolver,
            )
        logger.debug("Collecting compiled class from ${kotlinCompiler.rule_classes_path}")
        val compiledClassFiles =
            CollectorEngine()
                .fileFilter {
                    it.endsWith(".class")
                }.collect(kotlinCompiler.rule_classes_path)
                .toSet()
        logger.debug("found ${compiledClassFiles.size} compiled rules in ${kotlinCompiler.rule_classes_path}")

        val instances =
            compiledClassFiles
                .mapNotNull { classFile ->
                    try {
                        val clazz = Thread.currentThread().contextClassLoader.loadClass(classFile.absolutePath)
                        if (clazz == null) {
                            logger.warn("can't load rule class ${classFile.absolutePath}")
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
                logger.info("applying rule $functionName (from ${instance::class.qualifiedName}) ...")
                val f = instance.javaClass.getMethod(functionName, KonsistKspScopeCreator::class.java)
                try {
                    f.invoke(instance, konsistScopeCreator)
                    logger.debug("successfully applied rule $functionName from ${instance::class.qualifiedName}")
                } catch (ex: InvocationTargetException) {
                    if (ex.cause is KonsistKspKoAssertionFailedException) {
                        handleAssertionException(ex.cause as KonsistKspKoAssertionFailedException)
                    } else {
                        throw ex
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
                is KonsistKspKoFileDeclaration -> logger.error(ex.testName, it.file)
                is KonsistKspKoImportDeclaration -> {
                    val message =
                        "${ex.testName} but found 'import ${it.importString}' at file://${it.location}:${it.konsistKspImport.location.lineNumber}"
                    logger.error(message, it.konsistKspImport)
                }

                else -> logger.error("${it::class.java}")
            }
        }
    }
}
