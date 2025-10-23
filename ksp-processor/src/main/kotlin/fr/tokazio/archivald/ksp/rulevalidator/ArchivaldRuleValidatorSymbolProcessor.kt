package fr.tokazio.archivald.ksp.rulevalidator

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import fr.tokazio.archivald.konsist.KonsistRuleValidator
import fr.tokazio.archivald.ksp.bridge.ArchivaldKspSymbolResolver
import fr.tokazio.archivald.ksp.bridge.logger.ArchivaldKspLogger
import fr.tokazio.archivald.ksp.bridge.model.ArchivaldKspAnnotated

class ArchivaldRuleValidatorSymbolProcessor(
    logger: ArchivaldKspLogger,
    options: Map<String, String>,
) : SymbolProcessor {
    private val ruleValidator =
        KonsistRuleValidator(
            logger = logger,
            options = options,
        )

    @OptIn(KspExperimental::class)
    override fun process(resolver: Resolver): List<KSAnnotated> =
        ruleValidator.process(ArchivaldKspSymbolResolver(resolver)).map {
            (it as ArchivaldKspAnnotated).inner
        }
}
