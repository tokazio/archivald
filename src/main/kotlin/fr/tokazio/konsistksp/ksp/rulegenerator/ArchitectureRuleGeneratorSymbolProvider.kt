package fr.tokazio.konsistksp.ksp.rulegenerator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import fr.tokazio.konsistksp.ksp.CommonSymbolProcessorProvider
import fr.tokazio.konsistksp.ksp.rulevalidator.KonsistValidatorNoOpSymbolProcessor
import fr.tokazio.konsistksp.logger.KonsistKspLogger

class ArchitectureRuleGeneratorSymbolProvider : CommonSymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
       displayOptions(environment)
        return if (environment.options["konsistKspClasspath"] == null) {
            ArchitectureRuleGeneratorSymbolProcessor(
                logger = KonsistKspLogger(environment.logger, "true" == environment.options["konsistKspDebug"]),
                options = environment.options,
            )
        }else {
            environment.logger.info("Konsist ksp used as a 'validator' (using KonsistSymbolProcessor) because a 'konsistKspClasspath' configuration was found")
            KonsistValidatorNoOpSymbolProcessor()
        }
    }

    override val name: String
        get() = "Konsist ksp (generator)"
}
