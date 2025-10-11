package fr.tokazio.konsistksp.api

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
}
