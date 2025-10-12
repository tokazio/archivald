package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.Declaration
import fr.tokazio.konsistksp.internal.model.File
import fr.tokazio.konsistksp.internal.model.Node

class KonsistKspFile(
    internal val inner: KSFile,
) : File {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override val packageName: String by lazy {
        inner.packageName.asString()
    }

    override val filePath: String by lazy {
        inner.filePath
    }

    override val fileName: String = inner.fileName

    override val declarations: Sequence<Declaration> by lazy {
        inner.declarations
            .mapNotNull {
                when (it) {
                    is KSClassDeclaration -> KonsistKspClassDeclaration(it, inner)
                    is KSFunctionDeclaration -> KonsistKspFunctionDeclaration(it)
                    else -> null
                }
            }
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.annotations.map {
            KonsistKspAnnotation(it)
        }
    }

    override fun toString(): String = inner.toString()
}
