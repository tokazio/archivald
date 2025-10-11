package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSAnnotation
import fr.tokazio.konsistksp.api.Annotation
import fr.tokazio.konsistksp.api.Node

class KonsistKspAnnotation(
    internal val inner: KSAnnotation,
) : Annotation {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override fun toString(): String = inner.toString()
}
