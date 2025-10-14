package fr.tokazio.konsistksp.ksp.bridge.logger

import com.google.devtools.ksp.processing.KSPLogger
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.Node
import fr.tokazio.konsistksp.ksp.bridge.model.*

private const val PREFIX = "Konsist-ksp"

class KonsistKspLogger(
    private val logger: KSPLogger,
    private val isDebug: Boolean?,
) : Logger {
    override fun debug(
        message: String,
        node: Node?,
    ) {
        if (isDebug == true) {
            logger.info("[DEBUG] $PREFIX $message", node.asKSNodeOrNull())
        }
    }

    override fun info(
        message: String,
        node: Node?,
    ) {
        logger.info("[INFO ] $PREFIX $message", node.asKSNodeOrNull())
    }

    override fun warn(
        message: String,
        node: Node?,
    ) {
        logger.warn("[WARN ] $PREFIX $message", node.asKSNodeOrNull())
    }

    override fun error(
        message: String,
        node: Node?,
    ) {
        logger.error("[ERROR] $PREFIX $message", node.asKSNodeOrNull())
    }

    override fun exception(throwable: Throwable) {
        logger.exception(throwable)
    }
}

private fun Node?.asKSNodeOrNull() =
    this?.let {
        when (it) {
            is KonsistKspNode -> it.inner
            is KonsistKspFile -> it.inner
            is KonsistKspClassDeclaration -> it.inner
            is KonsistKspPropertyDeclaration -> it.inner
            is KonsistKspFunctionDeclaration -> it.inner
            is KonsistKspValueParameter -> it.inner
            else -> throw UnsupportedOperationException("Can't log a non KSNode ${it::class.java.simpleName} $it")
        }
    }
