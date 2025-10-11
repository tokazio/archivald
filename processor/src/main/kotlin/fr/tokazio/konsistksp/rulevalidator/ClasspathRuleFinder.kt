package fr.tokazio.konsistksp.rulevalidator

import fr.tokazio.konsistksp.api.Logger
import java.io.File
import java.util.jar.JarFile

class ClasspathRuleFinder(
    private val logger: Logger,
    private val classpath: List<String>,
) : RuleFinder {
    override fun find(): Set<String> {
        logger.debug("Searching META-INF/architecture-rules in ${classpath.size} jars from konsistKspClasspath")
        val withRules =
            classpath.mapNotNull { jarFile ->
                logger.debug("in $jarFile")
                if (File(jarFile).exists()) {
                    JarFile(jarFile).use { jar ->
                        val ruleFile =
                            jar.entries().asSequence().firstOrNull { it.name == "META-INF/architecture-rules" }
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

        logger.debug("${withRules.size} jar(s) with META-INF/architecture-rules:")
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
