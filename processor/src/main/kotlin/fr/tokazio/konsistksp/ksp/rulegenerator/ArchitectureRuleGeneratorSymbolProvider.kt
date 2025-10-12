package fr.tokazio.konsistksp.ksp.rulegenerator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import fr.tokazio.konsistksp.ksp.CommonSymbolProcessorProvider
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_CLASSPATH_OPTION
import fr.tokazio.konsistksp.ksp.KONSIST_KSP_DEBUG_OPTION
import fr.tokazio.konsistksp.ksp.bridge.logger.KonsistKspLogger
import fr.tokazio.konsistksp.ksp.rulevalidator.KonsistValidatorNoOpSymbolProcessor
import fr.tokazio.konsistksp.ksp.rulevalidator.KonsistValidatorSymbolProcessor

class ArchitectureRuleGeneratorSymbolProvider : CommonSymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val logger = KonsistKspLogger(environment.logger, "true" == environment.options[KONSIST_KSP_DEBUG_OPTION])
        displayOptions(logger, environment)
        return if (environment.options[KONSIST_KSP_CLASSPATH_OPTION] == null) {
            ArchitectureRuleGeneratorSymbolProcessor(
                logger = logger,
                options = environment.options,
            )
        } else {
            logger.info(
                "used as a 'validator' (using ${KonsistValidatorSymbolProcessor::class.java.simpleName}) because a '$KONSIST_KSP_CLASSPATH_OPTION' configuration was found",
            )
            KonsistValidatorNoOpSymbolProcessor()
        }
    }

    override val name: String
        get() = "Konsist ksp (generator)"
}
