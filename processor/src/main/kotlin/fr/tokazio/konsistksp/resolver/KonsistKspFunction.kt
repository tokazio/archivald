package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.konsistksp.api.ValueParameter

class KonsistKspFunction(
    internal val inner: KSFunctionDeclaration
): fr.tokazio.konsistksp.api.Function {
    override val parameters: List<ValueParameter> by lazy {
        inner.parameters.map{
            KonsistKspValueParameter(it)
        }
    }
}