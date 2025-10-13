package fr.tokazio.konsistksp.rulegenerator

import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.internal.RuleProcessor
import fr.tokazio.konsistksp.internal.SymbolResolver
import fr.tokazio.konsistksp.internal.model.Annotated
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_PROJECT_BASE_OPTION
import fr.tokazio.konsistksp.ksp.TARGET_RULES_FILE
import fr.tokazio.konsistksp.ksp.bridge.logger.KonsistKspLogger
import fr.tokazio.konsistksp.ksp.bridge.model.asClazz
import fr.tokazio.konsistksp.ksp.bridge.model.asFunction
import org.jetbrains.kotlin.incremental.createDirectory
import java.io.File

// Should not use com.google.devtools.ksp
// Should not use com.lemonappdev.konsist

/**
 * Generate a META-INF/`TARGET_RULES_FILE` in a project to lists the classes and functions marked as `@ArchitectureRule`
 * This file will be used by the validator to find rules from jars given in the classpath (using the compiler option KONSIST_KSP_CLASSPATH_OPTION)
 */
class RuleGenerator(
    private val logger: KonsistKspLogger,
    options: Map<String, String>,
) : RuleProcessor {
    private val projectBase: File = File(options[KONSIST_KSP_PROJECT_BASE_OPTION]!!)

    override fun process(resolver: SymbolResolver): List<Annotated> {
        logger.info("Add @${ArchitectureRule::class.java.simpleName} to META-INF/$TARGET_RULES_FILE...")
        val metaInf =
            projectBase
                .toPath()
                .resolve("src/main/resources/META-INF")
                .toFile()
        if (!metaInf.exists()) {
            metaInf.createDirectory()
        }
        val file = File("${metaInf.absolutePath}/$TARGET_RULES_FILE")
        if (file.exists()) {
            file.delete()
        }
        resolver.getSymbolsWithAnnotation(ArchitectureRule::class).forEach { annotated ->
            val function = annotated.asFunction()
            val ruleDescription = "${annotated.parent.asClazz().qualifiedName}::$function::${
                function.parameters.map { it.typeReference }.joinToString()
            }"
            logger.info("@${ArchitectureRule::class.java.simpleName} on $ruleDescription")
            file.appendText("$ruleDescription\n")
        }
        return emptyList()
    }
}
