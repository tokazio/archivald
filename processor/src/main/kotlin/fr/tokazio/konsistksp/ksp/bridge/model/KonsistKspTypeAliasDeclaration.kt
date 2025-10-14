package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSTypeAlias
import fr.tokazio.konsistksp.internal.model.*
import fr.tokazio.konsistksp.internal.model.Annotation

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

    override fun toString(): String = inner.toString()
}
