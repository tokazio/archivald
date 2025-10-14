package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSAnnotation
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.Location
import fr.tokazio.konsistksp.internal.model.Node

class KonsistKspAnnotation(
    internal val inner: KSAnnotation,
) : Annotation {
    override val name: String = inner.shortName.asString()

    override val parent: Node? by lazy {
        inner.parent?.let { KonsistKspNode(it) }
    }

    override val location: Location by lazy { KonsistKspLocation(inner.location as FileLocation) }

    override val fullyQualifiedName: String by lazy {
        inner.annotationType
            .resolve()
            .declaration
            .let { declaration ->
                declaration.qualifiedName
                    ?.asString()
                    ?: inner.notInClassPath()
            }
    }

    override fun toString(): String = inner.toString()
}
