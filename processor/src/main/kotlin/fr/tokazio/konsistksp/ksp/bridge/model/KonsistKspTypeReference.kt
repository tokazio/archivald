package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSTypeReference
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.Location
import fr.tokazio.konsistksp.internal.model.Node
import fr.tokazio.konsistksp.internal.model.TypeReference

class KonsistKspTypeReference(
    internal val inner: KSTypeReference,
) : TypeReference {
    override val parent: Node? by lazy { inner.parent?.let { KonsistKspNode(it) } }

    override val location: Location by lazy {
        KonsistKspLocation(inner.location as FileLocation)
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.extractAnnotations()
    }

    override val name: String by lazy {
        inner
            .resolve()
            .declaration
            .let { declaration ->
                declaration.qualifiedName
                    ?.asString() ?: declaration.notInClassPath()
            }
    }

    override fun toString(): String = inner.toString()
}
