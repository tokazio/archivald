package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSTypeReference
import fr.tokazio.archivald.internal.model.*
import fr.tokazio.archivald.internal.model.Annotation

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

    override fun asClass(): ClassDeclaration =
        KonsistKspClassDeclaration((this as KonsistKspNode).inner as KSClassDeclaration, findFile(inner))

    override fun asFunction(): FunctionDeclaration =
        KonsistKspFunctionDeclaration(
            (this as ArchivaldKspAnnotated).inner as KSFunctionDeclaration,
        )

    override fun toString(): String = inner.toString()
}
