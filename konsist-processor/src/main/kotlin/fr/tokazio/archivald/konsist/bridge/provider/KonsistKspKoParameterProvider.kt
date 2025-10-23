package fr.tokazio.archivald.konsist.bridge.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider

interface KonsistKspKoParameterProvider : KoParametersProvider {
    override fun countParameters(predicate: (KoParameterDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllParameters(predicate: (KoParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameter(predicate: (KoParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameterWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameterWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameters(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParametersWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParametersWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }
}
