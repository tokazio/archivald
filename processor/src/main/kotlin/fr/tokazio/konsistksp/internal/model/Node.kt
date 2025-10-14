package fr.tokazio.konsistksp.internal.model

interface Node {
    val parent: Node?
    val location: Location

    override fun toString(): String // Force toString definition
}
