package fr.tokazio.konsistksp.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.collector.CollectorEngine
import fr.tokazio.konsistksp.konsist.KonsistKspKoFileDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoImportDeclaration
import fr.tokazio.konsistksp.konsist.KonsistKspKoScopeCreator
import fr.tokazio.konsistksp.konsist.KspKoAssertionFailedException
import fr.tokazio.konsistksp.kotlin.KotlinCompiler
import fr.tokazio.konsistksp.logger.KonsistKspLogger
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.util.jar.JarFile
import kotlin.reflect.full.functions

class KonsistSymbolProcessor(
  private val logger: KonsistKspLogger,
  options: Map<String, String>,
) : SymbolProcessor {
  private lateinit var konsistScopeCreator: KonsistKspKoScopeCreator

  private val projectBase: File = File(options["konsistKspProjectBase"]!!)

  private val ruleClassFiles: Set<File>
  private val ruleSrcFiles: Set<File>

  private val compiler = KotlinCompiler(logger, projectBase, options)

  init {
    logger.debug("detected base project folder as ${projectBase.absolutePath}")
    // TODO how to give ksp the different folder where to find the rules ?
    ruleClassFiles = emptySet()
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

    val cp = options["konsistKspClasspath"]?.split(":")?:emptyList()
    logger.debug("Searching META-INF/architecture-rules in ${cp.size} jars from konsistKspClasspath")
    val withRules= cp.filter { jarFile ->
      logger.debug("in $jarFile")
      if(File(jarFile).exists()) {
        JarFile(jarFile).use { jar ->
          jar.entries().asSequence().any { it.name == "META-INF/architecture-rules" }
        }
      } else {
        logger.debug("$jarFile doesn't exists ?!")
        false
      }
    }

    logger.debug("${withRules.size} jar(s) with META-INF/architecture-rules:")
    withRules.forEach {
      logger.debug("* $it")
    }

    val srcInFolder = "${projectBase.absolutePath}/src/rules"
    logger.debug("compiling from $srcInFolder")
    ruleSrcFiles = CollectorEngine()
      .fileFilter {
        it.endsWith(".kt")
      }.collect(srcInFolder)
      .toSet()
    compiler.compile(ruleSrcFiles)
    logger.debug("compiled $srcInFolder")
    Thread
      .currentThread()
      .setContextClassLoader(FileClassLoader(logger, this::class.java.classLoader))
  }

  @OptIn(KspExperimental::class)
  override fun process(resolver: Resolver): List<KSAnnotated> {
    logger.info("analysis...")
    konsistScopeCreator = KonsistKspKoScopeCreator(
      logger,
      resolver,
    )

    val compiledClassFiles = CollectorEngine()
      .fileFilter {
        it.endsWith(".class")
      }.collect(compiler.rule_classes_path)
      .toSet()
    logger.debug("found ${compiledClassFiles.size} compiled rules in ${compiler.rule_classes_path}")

    val instances = compiledClassFiles
      .mapNotNull { classFile ->
        try {
          val clazz = Thread.currentThread().contextClassLoader.loadClass(classFile.absolutePath)
          if (clazz == null) {
            logger.warn("can't load rule class ${classFile.absolutePath}")
            null
          } else {
            logger.debug("loaded rule class $clazz")
            val functionNames = clazz.kotlin.functions
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
              val instance = clazz.newInstance()
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
        val f = instance.javaClass.getMethod(functionName, KonsistKspKoScopeCreator::class.java)
        try {
          f.invoke(instance, konsistScopeCreator)
          logger.debug("successfully applied rule $functionName from ${instance::class.qualifiedName}")
        } catch (ex: InvocationTargetException) {
          if (ex.cause is KspKoAssertionFailedException) {
            handleAssertionException(ex.cause as KspKoAssertionFailedException)
          } else {
            throw ex
          }
        }
      }
    }
    return emptyList()
  }

  private fun handleAssertionException(ex: KspKoAssertionFailedException) {
    ex.failedItems.forEach {
      logger.debug("rule failed at ${it::class.java.name} level")
      when (it) {
        is KonsistKspKoFileDeclaration -> logger.error(ex.testName, it.ksFile)
        is KonsistKspKoImportDeclaration -> {
          val message = "${ex.testName} but found 'import ${it.importString}' at file://${it.location}:${it.ksImport.location.lineNumber}"
          logger.error(message, it.ksImport)
        }

        else -> logger.error("${it::class.java}")
      }
    }
  }

}
