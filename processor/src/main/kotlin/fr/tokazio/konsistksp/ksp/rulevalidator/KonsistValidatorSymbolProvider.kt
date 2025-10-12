package fr.tokazio.konsistksp.ksp.rulevalidator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import fr.tokazio.konsistksp.ksp.CommonSymbolProcessorProvider
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_CLASSPATH_OPTION
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_DEBUG_OPTION
import fr.tokazio.konsistksp.ksp.TARGET_RULES_FILE
import fr.tokazio.konsistksp.ksp.bridge.logger.KonsistKspLogger
import fr.tokazio.konsistksp.ksp.rulegenerator.ArchitectureRuleGeneratorSymbolProcessor

class KonsistValidatorSymbolProvider : CommonSymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val logger = KonsistKspLogger(environment.logger, "true" == environment.options[KONSIST_KSP_DEBUG_OPTION])
        displayOptions(logger, environment)
        return if (environment.options[KONSIST_KSP_CLASSPATH_OPTION] != null) {
            KonsistValidatorSymbolProcessor(
                logger = logger,
                options = environment.options,
            )
        } else {
            logger.debug(
                "used as a META-INF/$TARGET_RULES_FILE 'generator' (using ${ArchitectureRuleGeneratorSymbolProcessor::class.java.simpleName}) because no '$KONSIST_KSP_CLASSPATH_OPTION' configuration found",
            )
            KonsistValidatorNoOpSymbolProcessor()
        }
    }

    override val name: String
        get() = "Konsist ksp (validator)"
}
