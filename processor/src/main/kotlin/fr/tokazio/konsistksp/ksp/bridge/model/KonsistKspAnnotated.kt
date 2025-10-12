package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.konsistksp.internal.model.Annotated
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.FunctionDeclaration
import fr.tokazio.konsistksp.internal.model.Node

class KonsistKspAnnotated(
    internal val inner: KSAnnotated,
) : Annotated {
    override val parent: Node?
        get() = inner.parent?.let { KonsistKspNode(it) }

    override val annotations: Sequence<Annotation> by lazy {
        inner.annotations.map {
            KonsistKspAnnotation(it)
        }
    }

    override fun toString(): String = inner.toString()
}

fun Annotated.asFunction(): FunctionDeclaration =
    KonsistKspFunctionDeclaration(
        (this as KonsistKspAnnotated).inner as KSFunctionDeclaration,
    )
