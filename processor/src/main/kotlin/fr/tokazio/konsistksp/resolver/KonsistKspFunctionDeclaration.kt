package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.konsistksp.api.*
import fr.tokazio.konsistksp.api.Annotation

class KonsistKspFunctionDeclaration(
    internal val inner: KSFunctionDeclaration,
) : FunctionDeclaration {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override val parameters: List<ValueParameter> by lazy {
        inner.parameters.map {
            KonsistKspValueParameter(it)
        }
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.annotations.map {
            KonsistKspAnnotation(it)
        }
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
