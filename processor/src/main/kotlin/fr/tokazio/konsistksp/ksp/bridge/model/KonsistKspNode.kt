package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSNode
import fr.tokazio.konsistksp.internal.model.ClassDeclaration
import fr.tokazio.konsistksp.internal.model.Node

class KonsistKspNode(
    internal val inner: KSNode,
) : Node {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override fun toString(): String = inner.toString()
}

fun Node?.asClazz(): ClassDeclaration = KonsistKspClassDeclaration((this as KonsistKspNode).inner as KSClassDeclaration, findFile(inner))

private fun findFile(node: KSNode?): KSFile =
    node?.let {
        it as? KSFile ?: findFile(it.parent)
    } ?: throw IllegalStateException("No root File")
