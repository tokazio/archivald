package fr.tokazio.archivald.internal.model

interface Node {
    val parent: Node?
    val location: Location

    fun asClass(): ClassDeclaration

    override fun toString(): String // Force toString definition
}
