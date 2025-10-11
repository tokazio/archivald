package fr.tokazio.konsistksp.api

interface Annotated {
    val symbol: Symbol

    fun asFunction() : Function
}