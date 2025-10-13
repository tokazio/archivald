package fr.tokazio.konsistksp.rulevalidator.finder

import fr.tokazio.konsistksp.internal.collector.CollectorEngine
import fr.tokazio.konsistksp.internal.kotlin.FileClassLoader
import fr.tokazio.konsistksp.internal.kotlin.KotlinCompiler
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.rulevalidator.RuleFinder
import org.jetbrains.kotlin.utils.mapToSetOrEmpty
import java.io.File

/**
 * Find .class from src/rules if it exists
 */
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
            CollectorEngine(logger)
                .fileFilter {
                    it.endsWith(".kt")
                }.collect(srcInFolder)
                .toSet()
        logger.debug("Found ${ruleSrcFiles.size} to compile from $srcInFolder")
        return try {
            kotlinCompiler.compile(ruleSrcFiles)
            logger.debug("compiled $srcInFolder")

            logger.debug("Collecting compiled class from ${kotlinCompiler.rule_classes_path}")
            val compiledClassFiles =
                CollectorEngine(logger)
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
