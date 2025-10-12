package fr.tokazio.konsistksp.rules

import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.KonsistKspScopeCreator
import fr.tokazio.konsistksp.assertFalse

class NoJavaIoDependencies {
    @ArchitectureRule("No java.io dependencies should be used")
    fun noJavaIoDependencies(konsistScope: KonsistKspScopeCreator) {
        konsistScope
            .scopeFromPackage(BASE_PACKAGE)
            .files
            .flatMap {
                it.imports
            }.assertFalse { import ->
                import.hasNameStartingWith("java.io")
            }
    }
}
