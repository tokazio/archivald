package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.KSValueParameter
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.Node
import fr.tokazio.konsistksp.internal.model.TypeReference
import fr.tokazio.konsistksp.internal.model.ValueParameter

class KonsistKspValueParameter(
    internal val inner: KSValueParameter,
) : ValueParameter {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override val typeReference: TypeReference by lazy {
        KonsistKspTypeReference(inner.type)
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.annotations.map {
            KonsistKspAnnotation(it)
        }
    }

    override fun toString(): String = inner.toString()
}
