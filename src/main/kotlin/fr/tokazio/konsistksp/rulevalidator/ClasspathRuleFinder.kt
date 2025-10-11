package fr.tokazio.konsistksp.rulevalidator

import fr.tokazio.konsistksp.api.Logger
import java.io.File
import java.util.jar.JarFile

class ClasspathRuleFinder(
    private val logger: Logger,
    private val classpath:List<String>,
): RuleFinder {
    override fun find(){
        logger.debug("Searching META-INF/architecture-rules in ${classpath.size} jars from konsistKspClasspath")
        val withRules= classpath.filter { jarFile ->
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
    }
}