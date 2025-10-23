package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import fr.tokazio.archivald.internal.model.*
import fr.tokazio.archivald.internal.model.Annotation

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

    override fun asFunction(): FunctionDeclaration =
        KonsistKspFunctionDeclaration(
            (this as ArchivaldKspAnnotated).inner as KSFunctionDeclaration,
        )

    override fun asClass(): ClassDeclaration =
        KonsistKspClassDeclaration((this as KonsistKspNode).inner as KSClassDeclaration, findFile(inner))

    override fun toString(): String = inner.toString()
}
