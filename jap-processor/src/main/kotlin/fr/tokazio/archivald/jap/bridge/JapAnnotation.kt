package fr.tokazio.archivald.jap.bridge

import fr.tokazio.archivald.internal.model.Annotation
import fr.tokazio.archivald.internal.model.ClassDeclaration
import fr.tokazio.archivald.internal.model.Location
import fr.tokazio.archivald.internal.model.Node
import javax.lang.model.element.AnnotationMirror

class JapAnnotation(
    val inner: AnnotationMirror,
) : Annotation {
    override val name: String
        get() = TODO("Not yet implemented")

    override val fullyQualifiedName: String
        get() = TODO("Not yet implemented")

    override val parent: Node?
        get() = TODO("Not yet implemented")

    override val location: Location
        get() = TODO("Not yet implemented")

    override fun asClass(): ClassDeclaration {
        TODO("Not yet implemented")
    }

    override fun toString(): String = inner.toString()
}
