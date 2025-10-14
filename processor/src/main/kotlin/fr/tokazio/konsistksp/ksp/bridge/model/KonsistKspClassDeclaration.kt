package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import fr.tokazio.konsistksp.internal.model.*
import fr.tokazio.konsistksp.internal.model.Annotation

class KonsistKspClassDeclaration(
    internal val inner: KSClassDeclaration,
    internal val file: KSFile,
) : ClassDeclaration {
    override val parent: Node? by lazy {
        inner.parent?.let { KonsistKspNode(it) }
    }

    override val location: Location by lazy {
        KonsistKspLocation(inner.location as FileLocation)
    }
    override val simpleName: String = inner.simpleName.asString()

    override val qualifiedName: String = inner.qualifiedName?.asString() ?: inner.notInClassPath()

    override val packageName: String = inner.packageName.asString()

    override val isCompanionObject: Boolean = inner.isCompanionObject

    override val isObject: Boolean = inner.classKind == ClassKind.OBJECT

    override val isInterface: Boolean = inner.classKind == ClassKind.INTERFACE

    override val isClass: Boolean = inner.classKind == ClassKind.CLASS

    override val isEnum: Boolean = inner.classKind == ClassKind.ENUM_CLASS

    override val isAnnotation: Boolean = inner.classKind == ClassKind.ANNOTATION_CLASS

    override val modifiers: Set<Modifier> by lazy {
        if (inner.isCompanionObject) {
            inner.extractModifiers() + Modifier.COMPANION
        } else {
            inner.extractModifiers()
        }
    }

    override val containingFile: File by lazy {
        KonsistKspFile(file)
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.extractAnnotations()
    }

    override val declarations: Sequence<Declaration> by lazy {
        inner.extractDeclarations()
    }

    override fun toString(): String = inner.toString()
}
