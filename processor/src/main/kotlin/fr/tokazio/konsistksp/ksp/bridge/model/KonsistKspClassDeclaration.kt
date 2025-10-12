package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.konsistksp.internal.model.*
import fr.tokazio.konsistksp.internal.model.Annotation

class KonsistKspClassDeclaration(
    internal val inner: KSClassDeclaration,
    internal val file: KSFile,
) : ClassDeclaration {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }
    /*
    override val containingDeclaration: Any
        get() = KonsistKspFile(file!!)

    override val containingFile: File
        get() = KonsistKspFile(file!!)

         override val sourceSetName: String
        get() =
            file?.let {
                if (it.filePath.contains("/main/")) "main" else "?"
            } ?: "?"
     */

    override val qualifiedName: String = inner.qualifiedName!!.asString()

    override val packageName: String = qualifiedName.substringBeforeLast(".")

    override val isCompanionObject: Boolean = inner.isCompanionObject

    override val isObject: Boolean = inner.classKind == ClassKind.OBJECT

    override val isInterface: Boolean = inner.classKind == ClassKind.INTERFACE

    override val isClass: Boolean = inner.classKind == ClassKind.CLASS

    override val isEnum: Boolean = inner.classKind == ClassKind.ENUM_CLASS

    override val isAnnotation: Boolean = inner.classKind == ClassKind.ANNOTATION_CLASS

    override val modifiers: Set<Modifier> by lazy {
        val mods = mutableSetOf<Modifier>()
        if (inner.isCompanionObject) {
            mods.add(Modifier.COMPANION)
        }
        inner.modifiers.forEach {
            modifiers
            mods.add(Modifier.valueOf(it.name))
        }
        mods
    }

    override val containingFile: File = KonsistKspFile(file)

    override val annotations: Sequence<Annotation>
        get() =
            inner.annotations.map {
                KonsistKspAnnotation(it)
            }

    override val declarations: Sequence<Declaration> by lazy {
        inner.declarations
            .mapNotNull {
                when (it) {
                    is KSClassDeclaration -> KonsistKspClassDeclaration(it, inner.containingFile!!)
                    is KSFunctionDeclaration -> KonsistKspFunctionDeclaration(it)
                    else -> null
                }
            }
    }

    override fun toString(): String = inner.toString()
}
