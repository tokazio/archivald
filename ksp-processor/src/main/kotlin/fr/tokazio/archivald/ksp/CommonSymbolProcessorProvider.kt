package fr.tokazio.archivald.ksp

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import fr.tokazio.archivald.internal.ARCHIVALD_PROJECT_BASE_OPTION
import fr.tokazio.archivald.internal.logger.Logger

interface CommonSymbolProcessorProvider : SymbolProcessorProvider {
    val name: String

    fun displayOptions(
        logger: Logger,
        environment: SymbolProcessorEnvironment,
    ) {
        if (environment.options.isEmpty()) {
            logger.debug("$name option: no option found, define at least the '$ARCHIVALD_PROJECT_BASE_OPTION'")
        } else {
            environment.options.forEach {
                logger.debug("$name option: ${it.key}=${it.value}")
            }
        }
    }
}
