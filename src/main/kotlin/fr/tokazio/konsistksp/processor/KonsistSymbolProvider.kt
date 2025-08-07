package fr.tokazio.konsistksp.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import fr.tokazio.konsistksp.logger.KonsistKspLogger

class KonsistSymbolProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        environment.options.forEach {
            environment.logger.info("Konsist ksp option: ${it.key}=${it.value}")
        }
        return if (environment.options["konsistKspClasspath"] != null) {
            KonsistSymbolProcessor(
                logger = KonsistKspLogger(environment.logger, "true" == environment.options["konsistKspDebug"]),
                options = environment.options,
            )
        } else {
            environment.logger.info("Konsist ksp used as a META-INF/architecture-rules generator because no 'konsistKspClasspath' configuration found")
            KonsistNoOpSymbolProcessor()
        }
    }

}
