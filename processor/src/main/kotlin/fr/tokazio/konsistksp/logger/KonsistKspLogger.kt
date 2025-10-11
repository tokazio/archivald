package fr.tokazio.konsistksp.logger

import com.google.devtools.ksp.processing.KSPLogger
import fr.tokazio.konsistksp.api.Logger
import fr.tokazio.konsistksp.api.Symbol
import fr.tokazio.konsistksp.resolver.KonsistKspSymbol

private const val PREFIX = "Konsist ksp "

class KonsistKspLogger(
    private val logger: KSPLogger,
    private val isDebug: Boolean?,
) : Logger {
    override fun debug(
        message: String,
        symbol: Symbol?,
    ) {
        if (isDebug == true) {
            logger.info("[DEBUG] $PREFIX $message", symbol.asKSNodeOrNull())
        }
    }

    override fun info(
        message: String,
        symbol: Symbol?,
    ) {
        logger.info("[INFO ] $PREFIX $message", symbol.asKSNodeOrNull())
    }

    override fun warn(
        message: String,
        symbol: Symbol?,
    ) {
        logger.warn("[WARN ] $PREFIX $message", symbol.asKSNodeOrNull())
    }

    override fun error(
        message: String,
        symbol: Symbol?,
    ) {
        logger.error("[ERROR] $PREFIX $message", symbol.asKSNodeOrNull())
    }
}

private fun Symbol?.asKSNodeOrNull() = this?.let { (it as KonsistKspSymbol).inner }
