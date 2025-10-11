package fr.tokazio.konsistksp.rulevalidator

import fr.tokazio.konsistksp.api.Logger
import fr.tokazio.konsistksp.collector.CollectorEngine
import fr.tokazio.konsistksp.kotlin.FileClassLoader
import fr.tokazio.konsistksp.kotlin.KotlinCompiler
import org.jetbrains.kotlin.utils.mapToSetOrEmpty
import java.io.File

class SrcRuleFinder(
    private val logger: Logger,
    private val projectBase: File,
    private val kotlinCompiler: KotlinCompiler,
) : RuleFinder {
    override fun find(): Set<String> {
        Thread
            .currentThread()
            .setContextClassLoader(FileClassLoader(logger, this::class.java.classLoader))
        val srcInFolder = "${projectBase.absolutePath}/src/rules"
        logger.debug("compiling from $srcInFolder")
        val ruleSrcFiles =
            CollectorEngine()
                .fileFilter {
                    it.endsWith(".kt")
                }.collect(srcInFolder)
                .toSet()
        return try {
            kotlinCompiler.compile(ruleSrcFiles)
            logger.debug("compiled $srcInFolder")

            logger.debug("Collecting compiled class from ${kotlinCompiler.rule_classes_path}")
            val compiledClassFiles =
                CollectorEngine()
                    .fileFilter {
                        it.endsWith(".class")
                    }.collect(kotlinCompiler.rule_classes_path)
                    .mapToSetOrEmpty { it.absolutePath }
            logger.debug("found ${compiledClassFiles.size} compiled rules in ${kotlinCompiler.rule_classes_path}")
            compiledClassFiles
        } catch (e: Exception) {
            logger.error("Dynamic compilation of $srcInFolder has error ${e::class.java.simpleName}: ${e.message}")
            throw e
        }
    }
}
