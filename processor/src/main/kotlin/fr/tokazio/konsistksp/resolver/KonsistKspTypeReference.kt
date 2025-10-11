package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSTypeReference
import fr.tokazio.konsistksp.api.Annotation
import fr.tokazio.konsistksp.api.Node
import fr.tokazio.konsistksp.api.TypeReference

class KonsistKspTypeReference(
    internal val inner: KSTypeReference,
) : TypeReference {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override val annotations: Sequence<Annotation> by lazy {
        inner.annotations.map {
            KonsistKspAnnotation(it)
        }
    }

    override fun toString(): String = inner.toString()
}
