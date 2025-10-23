package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSNode
import fr.tokazio.archivald.internal.model.ClassDeclaration
import fr.tokazio.archivald.internal.model.Location
import fr.tokazio.archivald.internal.model.Node

class KonsistKspNode(
    internal val inner: KSNode,
) : Node {
    override val parent: Node? by lazy { inner.parent?.let { KonsistKspNode(it) } }

    override val location: Location by lazy { KonsistKspLocation(inner.location as FileLocation) }

    override fun asClass(): ClassDeclaration =
        KonsistKspClassDeclaration((this as KonsistKspNode).inner as KSClassDeclaration, findFile(inner))

    override fun toString(): String = inner.toString()
}

internal fun findFile(node: KSNode?): KSFile =
    node?.let {
        it as? KSFile ?: findFile(it.parent)
    } ?: throw IllegalStateException("No root File")
