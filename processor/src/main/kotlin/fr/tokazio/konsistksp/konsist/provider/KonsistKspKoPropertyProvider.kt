package fr.tokazio.konsistksp.konsist.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider

interface KonsistKspKoPropertyProvider : KoPropertyProvider {
    override fun numProperties(includeNested: Boolean): Int = properties(includeNested).size

    override fun countProperties(
        includeNested: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllProperties(
        includeNested: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasProperties(includeNested: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPropertiesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPropertiesWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasProperty(
        includeNested: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPropertyWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPropertyWithName(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }
}
