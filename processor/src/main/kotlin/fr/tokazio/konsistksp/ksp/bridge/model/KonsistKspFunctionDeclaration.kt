package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.konsistksp.internal.model.*
import fr.tokazio.konsistksp.internal.model.Annotation

class KonsistKspFunctionDeclaration(
    internal val inner: KSFunctionDeclaration,
) : FunctionDeclaration {
    override val name: String = inner.simpleName.asString()

    override val parent: Node? = inner.parent?.let { KonsistKspNode(it) }

    override val parameters: List<ValueParameter> by lazy {
        inner.parameters.map {
            KonsistKspValueParameter(it)
        }
    }
    override val returnType: TypeReference? = inner.returnType?.let { KonsistKspTypeReference(it) }

    override val modifiers: List<Modifier> by lazy {
        inner.modifiers
            .map {
                Modifier.valueOf(it.name)
            }.toList()
    }

    override val containingDeclaration: Declaration =
        when (inner.parent) {
            is KSClassDeclaration ->
                KonsistKspClassDeclaration(
                    inner.parent as KSClassDeclaration,
                    inner.containingFile!!,
                )

            else -> throw IllegalStateException(
                "Unhandled function containingDeclaration ${if (inner.parent != null) inner.parent!!::class.java.simpleName else "null"} ",
            )
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
