package fr.tokazio.konsistksp.api

interface Node {
    val parent: Node?

    override fun toString(): String
}
