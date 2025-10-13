package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.KSAnnotation
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.Node

class KonsistKspAnnotation(
    internal val inner: KSAnnotation,
) : Annotation {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override val name: String = inner.shortName.asString()

    override val fullyQualifiedName: String =
        inner.annotationType
            .resolve()
            .declaration
            .qualifiedName
            ?.asString()
            ?: throw IllegalStateException("Can't get FQName of ${inner.shortName.asString()} because it's not in the classpath")

    override fun toString(): String = inner.toString()
}
