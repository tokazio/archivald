package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSValueParameter
import fr.tokazio.konsistksp.api.Type
import fr.tokazio.konsistksp.api.ValueParameter

class KonsistKspValueParameter(
    internal val inner: KSValueParameter,
) : ValueParameter {
    override val type: Type by lazy {
        KonsistKspType(inner.type)
    }

    override fun toString(): String = inner.toString()
}
