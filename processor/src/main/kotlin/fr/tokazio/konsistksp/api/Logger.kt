package fr.tokazio.konsistksp.api

interface Logger {
    fun debug(
        message: String,
        symbol: Symbol? = null,
    )

    fun info(
        message: String,
        symbol: Symbol? = null,
    )

    fun warn(
        message: String,
        symbol: Symbol? = null,
    )

    fun error(
        message: String,
        symbol: Symbol? = null,
    )
}