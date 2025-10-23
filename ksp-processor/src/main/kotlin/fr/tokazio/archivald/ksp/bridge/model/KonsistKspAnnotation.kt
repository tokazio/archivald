package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import fr.tokazio.archivald.internal.model.Annotation
import fr.tokazio.archivald.internal.model.ClassDeclaration
import fr.tokazio.archivald.internal.model.Location
import fr.tokazio.archivald.internal.model.Node

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

    override fun asClass(): ClassDeclaration =
        KonsistKspClassDeclaration((this as KonsistKspNode).inner as KSClassDeclaration, findFile(inner))

    override fun toString(): String = inner.toString()
}
