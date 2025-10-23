package fr.tokazio.archivald.jap.bridge

import fr.tokazio.archivald.internal.model.*
import fr.tokazio.archivald.internal.model.Annotation
import javax.lang.model.element.Element

class JapAnnotated(
    private val inner: Element,
) : Annotated {
    override val annotations: Sequence<Annotation> by lazy {
        inner.annotationMirrors.asSequence().map { annotationMirror ->
            JapAnnotation(annotationMirror)
        }
    }

    override fun asFunction(): FunctionDeclaration {
        TODO("Not yet implemented")
    }

    override val parent: Node?
        get() = TODO("Not yet implemented")

    override val location: Location
        get() = TODO("Not yet implemented")

    override fun asClass(): ClassDeclaration {
        TODO("Not yet implemented")
    }

    override fun toString(): String = inner.toString()
}
