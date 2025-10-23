package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSTypeAlias
import fr.tokazio.archivald.internal.model.*
import fr.tokazio.archivald.internal.model.Annotation

class KonsistKspTypeAliasDeclaration(
    internal val inner: KSTypeAlias,
) : TypeAlias {
    override val simpleName: String = inner.simpleName.asString()

    override val qualifiedName: String = inner.qualifiedName?.asString() ?: inner.notInClassPath()

    override val packageName: String = inner.packageName.asString()

    override val containingFile: File by lazy {
        KonsistKspFile(inner.containingFile!!)
    }

    override val declarations: Sequence<Declaration> by lazy {
        throw UnsupportedOperationException("A typealias haven't declarations")
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.extractAnnotations()
    }

    override val parent: Node? by lazy { inner.parent?.let { KonsistKspNode(it) } }

    override val location: Location by lazy {
        KonsistKspLocation(inner.location as FileLocation)
    }

    override fun asFunction(): FunctionDeclaration =
        KonsistKspFunctionDeclaration(
            (this as ArchivaldKspAnnotated).inner as KSFunctionDeclaration,
        )

    override fun asClass(): ClassDeclaration =
        KonsistKspClassDeclaration((this as KonsistKspNode).inner as KSClassDeclaration, findFile(inner))

    override fun toString(): String = inner.toString()
}
