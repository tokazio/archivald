package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.archivald.internal.model.*

class ArchivaldKspAnnotated(
    internal val inner: KSAnnotated,
) : Annotated {
    override val parent: Node? by lazy {
        inner.parent?.let { KonsistKspNode(it) }
    }

    override val location: Location by lazy { KonsistKspLocation(inner.location as FileLocation) }

    override val annotations: Sequence<fr.tokazio.archivald.internal.model.Annotation> by lazy {
        inner.extractAnnotations()
    }

    override fun asFunction(): FunctionDeclaration =
        KonsistKspFunctionDeclaration(
            (this as ArchivaldKspAnnotated).inner as KSFunctionDeclaration,
        )

    override fun asClass(): ClassDeclaration =
        KonsistKspClassDeclaration((this as KonsistKspNode).inner as KSClassDeclaration, findFile(inner))

    override fun toString(): String = inner.toString()
}
