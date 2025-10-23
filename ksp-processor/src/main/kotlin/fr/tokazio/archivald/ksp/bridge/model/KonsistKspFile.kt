package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.archivald.internal.model.*
import fr.tokazio.archivald.internal.model.Annotation

class KonsistKspFile(
    internal val inner: KSFile,
) : File {
    override val fileName: String = inner.fileName

    override val simpleName: String = inner.fileName

    override val qualifiedName: String = "${inner.packageName}.$fileName"

    override val parent: Node? by lazy {
        inner.parent?.let { KonsistKspNode(it) }
    }

    override val location: Location by lazy { KonsistKspLocation(inner.location as FileLocation) }

    override val packageName: String = inner.packageName.asString()

    override val containingFile: File by lazy {
        KonsistKspFile(inner)
    }

    override val filePath: String = inner.filePath

    override val declarations: Sequence<Declaration> by lazy {
        inner.extractDeclarations()
    }

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
