package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.konsistksp.api.Annotated
import fr.tokazio.konsistksp.api.Function
import fr.tokazio.konsistksp.api.Symbol

class KonsistKspAnnotated(
    internal val inner: KSAnnotated,
) : Annotated {
    override val symbol: Symbol
        get() = KonsistKspSymbol(inner.parent)

    override fun asFunction(): Function =
        KonsistKspFunction(
            inner as KSFunctionDeclaration,
        )

    override fun toString(): String = inner.toString()
}
