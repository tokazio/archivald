package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.KSAnnotation
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.Node

class KonsistKspAnnotation(
    internal val inner: KSAnnotation,
) : Annotation {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override fun toString(): String = inner.toString()
}
