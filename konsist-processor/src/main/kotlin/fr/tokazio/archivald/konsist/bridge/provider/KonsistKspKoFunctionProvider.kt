package fr.tokazio.archivald.konsist.bridge.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionProvider

interface KonsistKspKoFunctionProvider : KoFunctionProvider {
    override fun countFunctions(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllFunctions(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasFunction(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasFunctionWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasFunctionWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasFunctions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasFunctionsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasFunctionsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numFunctions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }
}
