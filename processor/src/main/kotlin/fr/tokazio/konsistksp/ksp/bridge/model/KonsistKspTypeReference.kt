package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.KSTypeReference
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.Node
import fr.tokazio.konsistksp.internal.model.TypeReference

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
