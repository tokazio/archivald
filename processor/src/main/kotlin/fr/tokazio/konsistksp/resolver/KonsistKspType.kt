package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSTypeReference
import fr.tokazio.konsistksp.api.Type

class KonsistKspType(
    internal val inner: KSTypeReference,
) : Type {
    override fun toString(): String = inner.toString()
}
