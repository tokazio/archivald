package fr.tokazio.archivald.ksp.rulevalidator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import fr.tokazio.archivald.internal.ARCHIVALD_CLASSPATH_OPTION
import fr.tokazio.archivald.internal.ARCHIVALD_DEBUG_OPTION
import fr.tokazio.archivald.internal.TARGET_RULES_FILE
import fr.tokazio.archivald.ksp.CommonSymbolProcessorProvider
import fr.tokazio.archivald.ksp.bridge.logger.ArchivaldKspLogger
import fr.tokazio.archivald.ksp.rulegenerator.ArchivaldRuleGeneratorSymbolProcessor

class ArchivaldRuleValidatorSymbolProvider : CommonSymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val logger = ArchivaldKspLogger(environment.logger, "true" == environment.options[ARCHIVALD_DEBUG_OPTION])
        displayOptions(logger, environment)
        return if (environment.options[ARCHIVALD_CLASSPATH_OPTION] != null) {
            ArchivaldRuleValidatorSymbolProcessor(
                logger = logger,
                options = environment.options,
            )
        } else {
            logger.debug(
                "used as a META-INF/$TARGET_RULES_FILE 'generator' (using ${ArchivaldRuleGeneratorSymbolProcessor::class.java.simpleName}) because no '$ARCHIVALD_CLASSPATH_OPTION' configuration found",
            )
            ArchivaldRuleNoOpValidatorSymbolProcessor()
        }
    }

    override val name: String
        get() = "Konsist ksp (validator)"
}
