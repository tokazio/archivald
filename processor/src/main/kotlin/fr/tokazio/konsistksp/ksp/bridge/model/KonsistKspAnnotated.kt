package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSAnnotated
import fr.tokazio.konsistksp.internal.model.Annotated
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.Location
import fr.tokazio.konsistksp.internal.model.Node

class KonsistKspAnnotated(
    internal val inner: KSAnnotated,
) : Annotated {
    override val parent: Node? by lazy {
        inner.parent?.let { KonsistKspNode(it) }
    }

    override val location: Location by lazy { KonsistKspLocation(inner.location as FileLocation) }

    override val annotations: Sequence<Annotation> by lazy {
        inner.extractAnnotations()
    }

    override fun toString(): String = inner.toString()
}
