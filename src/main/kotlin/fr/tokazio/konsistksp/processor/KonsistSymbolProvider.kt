package fr.tokazio.konsistksp.processor

import com.google.devtools.ksp.common.impl.CodeGeneratorImpl
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import fr.tokazio.konsistksp.logger.KonsistKspLogger

class KonsistSymbolProvider : SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    environment.options.forEach {
      environment.logger.info("Konsist ksp option: ${it.key}=${it.value}")
    }
    return KonsistSymbolProcessor(
      logger = KonsistKspLogger(environment.logger, "true" == environment.options["konsistKspDebug"]),
      options = environment.options,
      codeGenerator = environment.codeGenerator as CodeGeneratorImpl,
    )
  }
}
