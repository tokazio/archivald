package fr.tokazio.konsistksp.internal.logger

import fr.tokazio.konsistksp.internal.model.Node

interface Logger {
    fun debug(
        message: String,
        node: Node? = null,
    )

    fun info(
        message: String,
        node: Node? = null,
    )

    fun warn(
        message: String,
        node: Node? = null,
    )

    fun error(
        message: String,
        node: Node? = null,
    )

    fun exception(throwable: Throwable)
}
