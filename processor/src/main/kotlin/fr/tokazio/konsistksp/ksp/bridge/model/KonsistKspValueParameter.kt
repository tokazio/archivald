package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSValueParameter
import fr.tokazio.konsistksp.internal.model.*
import fr.tokazio.konsistksp.internal.model.Annotation

class KonsistKspValueParameter(
    internal val inner: KSValueParameter,
) : ValueParameter {
    override val name: String = inner.name!!.asString()

    override val parent: Node? by lazy { inner.parent?.let { KonsistKspNode(it) } }

    override val typeReference: TypeReference by lazy {
        KonsistKspTypeReference(inner.type)
    }

    override val containingDeclaration: Declaration by lazy {
        inner.extractParent()
    }

    override val location: Location by lazy { KonsistKspLocation(inner.location as FileLocation) }

    override val annotations: Sequence<Annotation> by lazy {
        inner.extractAnnotations()
    }

    override fun toString(): String = inner.toString()
}
