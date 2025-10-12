package fr.tokazio.konsistksp.internal.model

interface Node {
    val parent: Node?

    override fun toString(): String // Force toString definition
}
