package fr.tokazio.konsistksp.ksp.rulegenerator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import fr.tokazio.konsistksp.ksp.bridge.KonsistKspSymbolResolver
import fr.tokazio.konsistksp.ksp.bridge.logger.KonsistKspLogger
import fr.tokazio.konsistksp.ksp.bridge.model.KonsistKspAnnotated
import fr.tokazio.konsistksp.rulegenerator.RuleGenerator

class ArchitectureRuleGeneratorSymbolProcessor(
    logger: KonsistKspLogger,
    options: Map<String, String>,
) : SymbolProcessor {
    private val ruleGenerator =
        RuleGenerator(
            logger = logger,
            options = options,
        )

    @OptIn(KspExperimental::class)
    override fun process(resolver: Resolver): List<KSAnnotated> =
        ruleGenerator.process(KonsistKspSymbolResolver(resolver)).map {
            (it as KonsistKspAnnotated).inner
        }
}
