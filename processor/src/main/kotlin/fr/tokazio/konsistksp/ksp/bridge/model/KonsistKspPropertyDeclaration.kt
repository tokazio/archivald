package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import fr.tokazio.konsistksp.internal.model.*
import fr.tokazio.konsistksp.internal.model.Annotation

class KonsistKspPropertyDeclaration(
    internal val inner: KSPropertyDeclaration,
) : PropertyDeclaration {
    override val name: String = inner.simpleName.asString()

    override val parent: Node? by lazy { inner.parent?.let { KonsistKspNode(it) } }

    override val containingDeclaration: Declaration by lazy {
        inner.extractParent()
    }

    override val location: Location by lazy { KonsistKspLocation(inner.location as FileLocation) }

    override val simpleName: String = inner.simpleName.asString()

    override val qualifiedName: String = inner.qualifiedName?.asString() ?: inner.notInClassPath()

    override val packageName: String = inner.packageName.asString()

    override val containingFile: File by lazy { KonsistKspFile(inner.containingFile!!) }

    override val declarations: Sequence<Declaration> by lazy {
        throw UnsupportedOperationException("A property haven't declarations")
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.extractAnnotations()
    }

    override val modifiers: Set<Modifier> by lazy {
        inner.extractModifiers()
    }

    override fun toString(): String = inner.toString()
}
