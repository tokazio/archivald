package fr.tokazio.konsistksp.rules

import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.KonsistKspScopeCreator
import fr.tokazio.konsistksp.assertFalse

class NoJavaIoDependencies {
    @ArchitectureRule
    fun noJavaIoDependencies(konsistScope: KonsistKspScopeCreator) {
        konsistScope
            .scopeFromPackage(BASE_PACKAGE)
            .files
            .flatMap {
                it.imports
            }.assertFalse(testName = "should not use java.io dependencies") { import ->
                import.hasNameStartingWith("java.io")
            }
    }
}
