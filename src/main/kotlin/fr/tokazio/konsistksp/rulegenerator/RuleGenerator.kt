package fr.tokazio.konsistksp.rulegenerator

import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.api.Annotated
import fr.tokazio.konsistksp.api.RuleProcessor
import fr.tokazio.konsistksp.api.SymbolResolver
import fr.tokazio.konsistksp.logger.KonsistKspLogger
import org.jetbrains.kotlin.incremental.createDirectory
import java.io.File

class RuleGenerator(
    private val logger: KonsistKspLogger,
    options: Map<String, String>
): RuleProcessor {
    private val projectBase: File = File(options["konsistKspProjectBase"]!!)

    override fun process(resolver: SymbolResolver): List<Annotated> {
        logger.info("Add @ArchitectureRule to META-INF/architecture-rules...")
        val metaInf =
            File("${projectBase.absolutePath}${File.separatorChar}src${File.separatorChar}main${File.separatorChar}resources${File.separatorChar}META-INF")
        if (!metaInf.exists()) {
            metaInf.createDirectory()
        }
        val file = File("${metaInf.absolutePath}/architecture-rules")
        resolver.getSymbolsWithAnnotation(ArchitectureRule::class).forEach { annotated ->
            val ksFun = annotated.asFunction()
            val f = "${annotated.symbol}::$ksFun::${ksFun.parameters.map { it.type }.joinToString()}"
            logger.info("@ArchitectureRule on $f")
            file.appendText("$f\n")
        }
        return emptyList()

    }
}