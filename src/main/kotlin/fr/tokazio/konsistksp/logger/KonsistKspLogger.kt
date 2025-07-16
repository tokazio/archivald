package fr.tokazio.konsistksp.logger

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSNode

private const val PREFIX = "Konsist ksp "

class KonsistKspLogger(
  private val logger: KSPLogger,
  private val isDebug: Boolean?,
) {
  fun debug(
    message: String,
    symbol: KSNode? = null,
  ) {
    if (isDebug == true) {
      logger.info("[DEBUG] $PREFIX $message", symbol)
    }
  }

  fun info(
    message: String,
    symbol: KSNode? = null,
  ) {
    logger.info("$PREFIX $message", symbol)
  }

  fun warn(
    message: String,
    symbol: KSNode? = null,
  ) {
    logger.warn("$PREFIX $message", symbol)
  }

  fun error(
    message: String,
    symbol: KSNode? = null,
  ) {
    logger.error("$PREFIX $message", symbol)
  }
}
