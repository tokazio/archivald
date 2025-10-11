package fr.tokazio.konsistksp.rulevalidator

import fr.tokazio.konsistksp.api.Logger
import fr.tokazio.konsistksp.collector.CollectorEngine
import fr.tokazio.konsistksp.kotlin.FileClassLoader
import fr.tokazio.konsistksp.kotlin.KotlinCompiler
import java.io.File
import kotlin.reflect.jvm.jvmName

class SrcRuleFinder(
    private val logger: Logger,
    private val projectBase: File,
    private val kotlinCompiler: KotlinCompiler,
) : RuleFinder {
    override fun find() {
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
        try {
            kotlinCompiler.compile(ruleSrcFiles)
            logger.debug("compiled $srcInFolder")
        } catch (e: Exception) {
            logger.error("Dynamic compilation of $srcInFolder has error ${e::class.jvmName}: ${e.message}")
            throw e
        }
    }
}
