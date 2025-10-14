package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import fr.tokazio.konsistksp.internal.model.*
import fr.tokazio.konsistksp.internal.model.Annotation

class KonsistKspPropertyDeclaration(
    internal val inner: KSPropertyDeclaration,
) : PropertyDeclaration {
    override val name: String = inner.simpleName.asString()
    override val parent: Node? = inner.parent?.let { KonsistKspNode(it) }

    override val containingDeclaration: Declaration =
        when {
            inner.parentDeclaration is KSClassDeclaration ->
                KonsistKspClassDeclaration(
                    inner.parentDeclaration as KSClassDeclaration,
                    inner.containingFile!!,
                )

            else -> throw IllegalStateException("Unhandled property.containingDeclaration ${inner.parentDeclaration}")
        }

    override val location: Location = KonsistKspLocation((inner.location as FileLocation))

    override val declarations: Sequence<Declaration>
        get() = TODO("Should not be used")

    override val annotations: Sequence<Annotation> by lazy {
        inner.annotations.map {
            KonsistKspAnnotation(it)
        }
    }

    override val modifiers: List<Modifier> by lazy {
        inner.modifiers
            .map {
                Modifier.valueOf(it.name)
            }.toList()
    }

    override fun toString(): String = inner.toString()
}
