package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSNode
import fr.tokazio.konsistksp.internal.model.Location
import fr.tokazio.konsistksp.internal.model.Node

class KonsistKspNode(
    internal val inner: KSNode,
) : Node {
    override val parent: Node? by lazy { inner.parent?.let { KonsistKspNode(it) } }

    override val location: Location by lazy { KonsistKspLocation(inner.location as FileLocation) }

    override fun toString(): String = inner.toString()
}
