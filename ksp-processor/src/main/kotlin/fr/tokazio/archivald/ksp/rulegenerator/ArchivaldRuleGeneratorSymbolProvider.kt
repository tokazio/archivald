package fr.tokazio.archivald.ksp.rulegenerator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import fr.tokazio.archivald.internal.ARCHIVALD_CLASSPATH_OPTION
import fr.tokazio.archivald.internal.ARCHIVALD_DEBUG_OPTION
import fr.tokazio.archivald.ksp.CommonSymbolProcessorProvider
import fr.tokazio.archivald.ksp.bridge.logger.ArchivaldKspLogger
import fr.tokazio.archivald.ksp.rulevalidator.ArchivaldRuleNoOpValidatorSymbolProcessor
import fr.tokazio.archivald.ksp.rulevalidator.ArchivaldRuleValidatorSymbolProcessor

class ArchivaldRuleGeneratorSymbolProvider : CommonSymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val logger = ArchivaldKspLogger(environment.logger, "true" == environment.options[ARCHIVALD_DEBUG_OPTION])
        displayOptions(logger, environment)
        return if (environment.options[ARCHIVALD_CLASSPATH_OPTION] == null) {
            ArchivaldRuleGeneratorSymbolProcessor(
                logger = logger,
                options = environment.options,
            )
        } else {
            logger.debug(
                "used as a 'validator' (using ${ArchivaldRuleValidatorSymbolProcessor::class.java.simpleName}) because a '$ARCHIVALD_CLASSPATH_OPTION' configuration was found",
            )
            ArchivaldRuleNoOpValidatorSymbolProcessor()
        }
    }

    override val name: String
        get() = "Konsist ksp (generator)"
}
