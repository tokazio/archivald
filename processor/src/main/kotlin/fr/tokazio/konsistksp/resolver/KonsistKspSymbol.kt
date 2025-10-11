package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSNode
import fr.tokazio.konsistksp.api.Symbol

class KonsistKspSymbol(
    internal val inner: KSNode?,
) : Symbol {
    override fun toString(): String = inner.toString()
}
