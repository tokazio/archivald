package fr.tokazio.konsistksp.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class ArchitectureRuleSymbolProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        environment.options.forEach {
            environment.logger.info("Konsist ksp option: ${it.key}=${it.value}")
        }
        return if (environment.options["konsistKspClasspath"] == null) {
            ArchitectureRuleSymbolProcessor(
                logger = environment.logger,
                options = environment.options,
            )
        }else {
            environment.logger.info("Konsist ksp used as a validator because a 'konsistKspClasspath' configuration was found")
            KonsistNoOpSymbolProcessor()
        }
    }
}
