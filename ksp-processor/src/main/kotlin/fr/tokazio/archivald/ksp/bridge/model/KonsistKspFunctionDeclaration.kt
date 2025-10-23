package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.archivald.internal.model.*
import fr.tokazio.archivald.internal.model.Annotation

class KonsistKspFunctionDeclaration(
    internal val inner: KSFunctionDeclaration,
) : FunctionDeclaration {
    override val simpleName: String = inner.simpleName.asString()

    override val packageName: String = inner.packageName.asString()

    override val qualifiedName: String = inner.qualifiedName?.asString() ?: "$packageName.$simpleName"

    override val containingFile: File by lazy {
        KonsistKspFile(inner.containingFile!!)
    }

    override val parent: Node? by lazy {
        inner.parent?.let { KonsistKspNode(it) }
    }
    override val location: Location by lazy { KonsistKspLocation(inner.location as FileLocation) }

    override val parameters: List<ValueParameter> by lazy {
        inner.parameters.map {
            KonsistKspValueParameter(it)
        }
    }
    override val returnType: TypeReference? by lazy {
        inner.returnType?.let { KonsistKspTypeReference(it) }
    }

    override val modifiers: Set<Modifier> by lazy {
        inner.extractModifiers()
    }

    override val containingDeclaration: Declaration by lazy {
        inner.extractContainingDeclaration()
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.extractAnnotations()
    }

    override val declarations: Sequence<Declaration> by lazy {
        inner.extractDeclarations()
    }

    override fun asFunction(): FunctionDeclaration =
        KonsistKspFunctionDeclaration(
            (this as ArchivaldKspAnnotated).inner as KSFunctionDeclaration,
        )

    override fun asClass(): ClassDeclaration =
        KonsistKspClassDeclaration((this as KonsistKspNode).inner as KSClassDeclaration, findFile(inner))

    override fun toString(): String = inner.toString()
}
