package fr.tokazio.archivald.jap.bridge

import fr.tokazio.archivald.internal.logger.Logger
import fr.tokazio.archivald.internal.model.Node
import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.tools.Diagnostic

private const val PREFIX = "Archivald-ksp"

class JapLogger(
    private val messager: Messager,
    private val isDebug: Boolean?,
) : Logger {
    override fun debug(
        message: String,
        node: Node?,
    ) {
        if (isDebug == true) {
            messager.printMessage(Diagnostic.Kind.NOTE, "[DEBUG] $PREFIX $message", node.asElementOrNull())
        }
    }

    override fun info(
        message: String,
        node: Node?,
    ) {
        messager.printMessage(Diagnostic.Kind.NOTE, "[INFO ] $PREFIX $message", node.asElementOrNull())
    }

    override fun warn(
        message: String,
        node: Node?,
    ) {
        messager.printMessage(Diagnostic.Kind.WARNING, "[WARN ] $PREFIX $message", node.asElementOrNull())
    }

    override fun error(
        message: String,
        node: Node?,
    ) {
        messager.printMessage(Diagnostic.Kind.ERROR, "[ERROR] $PREFIX $message", node.asElementOrNull())
    }

    override fun exception(throwable: Throwable) {
        // Do nothing
    }
}

private fun Node?.asElementOrNull(): Element? = null
/*
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

 */
