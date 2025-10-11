package fr.tokazio.konsistksp.ksp.rulevalidator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import fr.tokazio.konsistksp.ksp.CommonSymbolProcessorProvider
import fr.tokazio.konsistksp.logger.KonsistKspLogger

class KonsistValidatorSymbolProvider : CommonSymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        displayOptions(environment)
        return if (environment.options["konsistKspClasspath"] != null) {
            KonsistValidatorSymbolProcessor(
                logger = KonsistKspLogger(environment.logger, "true" == environment.options["konsistKspDebug"]),
                options = environment.options,
            )
        } else {
            environment.logger.info("Konsist ksp used as a META-INF/architecture-rules 'generator' (using ArchitectureRuleSymbolProcessor) because no 'konsistKspClasspath' configuration found")
            KonsistValidatorNoOpSymbolProcessor()
        }
    }

    override val name: String
        get() = "Konsist ksp (validator)"

}
