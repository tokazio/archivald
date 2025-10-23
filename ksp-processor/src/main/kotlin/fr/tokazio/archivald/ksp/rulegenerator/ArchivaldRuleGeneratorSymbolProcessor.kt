package fr.tokazio.archivald.ksp.rulegenerator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import fr.tokazio.archivald.internal.logger.Logger
import fr.tokazio.archivald.ksp.bridge.ArchivaldKspSymbolResolver
import fr.tokazio.archivald.ksp.bridge.model.ArchivaldKspAnnotated
import fr.tokazio.archivald.rulegenerator.RuleGenerator

class ArchivaldRuleGeneratorSymbolProcessor(
    logger: Logger,
    options: Map<String, String>,
) : SymbolProcessor {
    private val ruleGenerator =
        RuleGenerator(
            logger = logger,
            options = options,
        )

    @OptIn(KspExperimental::class)
    override fun process(resolver: Resolver): List<KSAnnotated> =
        ruleGenerator.process(ArchivaldKspSymbolResolver(resolver)).map {
            (it as ArchivaldKspAnnotated).inner
        }
}
