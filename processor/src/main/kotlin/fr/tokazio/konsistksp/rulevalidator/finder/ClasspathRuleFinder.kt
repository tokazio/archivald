package fr.tokazio.konsistksp.rulevalidator.finder

import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_CLASSPATH_OPTION
import fr.tokazio.konsistksp.ksp.TARGET_RULES_FILE
import fr.tokazio.konsistksp.rulevalidator.RuleFinder
import java.io.File
import java.util.jar.JarFile

/**
 * Find .class from jars form the classpath
 * The classpath must be provided as the processor option `KONSIST_KSP_CLASSPATH_OPTION`
 */
class ClasspathRuleFinder(
    private val logger: Logger,
    private val classpath: List<String>,
) : RuleFinder {
    override fun find(): Set<String> {
        logger.debug("Searching META-INF/$TARGET_RULES_FILE in ${classpath.size} jars from '$KONSIST_KSP_CLASSPATH_OPTION'")
        val withRules =
            classpath.mapNotNull { jarFile ->
                logger.debug("in $jarFile")
                if (File(jarFile).exists()) {
                    JarFile(jarFile).use { jar ->
                        val ruleFile =
                            jar.entries().asSequence().firstOrNull { it.name == "META-INF/$TARGET_RULES_FILE" }
                        if (ruleFile != null) {
                            jarFile to String(jar.getInputStream(ruleFile).readAllBytes())
                        } else {
                            null
                        }
                    }
                } else {
                    logger.debug("$jarFile doesn't exists ?!")
                    null
                }
            }

        logger.debug("${withRules.size} jar(s) with META-INF/$TARGET_RULES_FILE:")
        return withRules
            .flatMap { (jarFile, content) ->
                logger.debug("* $jarFile")
                content.split("\n").map { line ->
                    "$jarFile://${line.split("::")[0]}"
                }
            }.filter {
                it.isNotEmpty()
            }.toSet()
    }
}
