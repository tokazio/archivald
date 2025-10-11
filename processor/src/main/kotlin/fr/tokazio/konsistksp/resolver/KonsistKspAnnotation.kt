package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSAnnotation
import fr.tokazio.konsistksp.api.Annotation

class KonsistKspAnnotation(
    internal val inner: KSAnnotation,
) : Annotation {
    override fun toString(): String = inner.toString()
}
