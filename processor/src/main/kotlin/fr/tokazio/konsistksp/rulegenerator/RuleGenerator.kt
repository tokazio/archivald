package fr.tokazio.konsistksp.rulegenerator

import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.api.Annotated
import fr.tokazio.konsistksp.api.RuleProcessor
import fr.tokazio.konsistksp.api.SymbolResolver
import fr.tokazio.konsistksp.logger.KonsistKspLogger
import fr.tokazio.konsistksp.resolver.asClazz
import fr.tokazio.konsistksp.resolver.asFunction
import org.jetbrains.kotlin.incremental.createDirectory
import java.io.File

class RuleGenerator(
    private val logger: KonsistKspLogger,
    options: Map<String, String>,
) : RuleProcessor {
    private val projectBase: File = File(options["konsistKspProjectBase"]!!)

    override fun process(resolver: SymbolResolver): List<Annotated> {
        logger.info("Add @ArchitectureRule to META-INF/architecture-rules...")
        val metaInf =
            File(
                "${projectBase.absolutePath}${File.separatorChar}src${File.separatorChar}main${File.separatorChar}resources${File.separatorChar}META-INF",
            )
        if (!metaInf.exists()) {
            metaInf.createDirectory()
        }
        val file = File("${metaInf.absolutePath}/architecture-rules")
        if (file.exists()) {
            file.delete()
        }
        resolver.getSymbolsWithAnnotation(ArchitectureRule::class).forEach { annotated ->
            val function = annotated.asFunction()
            val f = "${annotated.parent.asClazz().qualifiedName}::$function::${
                function.parameters.map { it.typeReference }.joinToString()
            }"
            logger.info("@ArchitectureRule on $f")
            file.appendText("$f\n")
        }
        return emptyList()
    }
}
