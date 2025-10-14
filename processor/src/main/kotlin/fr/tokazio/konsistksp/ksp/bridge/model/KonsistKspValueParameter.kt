package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import fr.tokazio.konsistksp.internal.model.*
import fr.tokazio.konsistksp.internal.model.Annotation

class KonsistKspValueParameter(
    internal val inner: KSValueParameter,
) : ValueParameter {
    override val name: String = inner.name!!.asString()

    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override val typeReference: TypeReference by lazy {
        KonsistKspTypeReference(inner.type)
    }
    override val containingDeclaration: Declaration =
        when (inner.parent) {
            is KSFunctionDeclaration -> KonsistKspFunctionDeclaration(inner.parent as KSFunctionDeclaration)
            else -> throw IllegalStateException(
                "Unhandled valueParameter containingDeclaration ${if (inner.parent != null) inner.parent!!::class.java.simpleName else "null"} ",
            )
        }
    override val location: Location = KonsistKspLocation(inner.location as FileLocation)

    override val modifiers: List<Modifier> by lazy {
        emptyList() // TODO
        /*
        inner.modifiers
            .map {
                Modifier.valueOf(it.name)
            }.toList()

         */
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.annotations.map {
            KonsistKspAnnotation(it)
        }
    }

    override fun toString(): String = inner.toString()
}
