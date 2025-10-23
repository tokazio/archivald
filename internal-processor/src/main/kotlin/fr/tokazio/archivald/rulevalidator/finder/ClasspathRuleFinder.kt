package fr.tokazio.archivald.rulevalidator.finder

import fr.tokazio.archivald.internal.ARCHIVALD_CLASSPATH_OPTION
import fr.tokazio.archivald.internal.TARGET_RULES_FILE
import fr.tokazio.archivald.internal.logger.Logger
import fr.tokazio.archivald.rulevalidator.RuleFinder
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
        logger.debug("Searching META-INF/${TARGET_RULES_FILE} in ${classpath.size} jars from '${ARCHIVALD_CLASSPATH_OPTION}'")
        val withRules =
            classpath.mapNotNull { jarFile ->
                logger.debug("in $jarFile")
                if (File(jarFile).exists()) {
                    JarFile(jarFile).use { jar ->
                        val ruleFile =
                            jar.entries().asSequence().firstOrNull { it.name == "META-INF/${TARGET_RULES_FILE}" }
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

        logger.debug("${withRules.size} jar(s) with META-INF/${TARGET_RULES_FILE}:")
        return withRules
            .flatMap { (jarFile, content) ->
                logger.debug("* $jarFile")
                content.split("\n").mapNotNull { line ->
                    val className = line.split("::")[0]
                    if (className.isNotBlank()) {
                        "$jarFile://$className"
                    } else {
                        null // Skip blank lines
                    }
                }
            }.filter {
                it.isNotEmpty()
            }.toSet()
    }
}
