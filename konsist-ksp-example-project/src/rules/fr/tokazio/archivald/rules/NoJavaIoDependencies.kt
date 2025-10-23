package fr.tokazio.archivald.rules

import fr.tokazio.archivald.ArchitectureRule
import fr.tokazio.archivald.konsist.KonsistKspScopeCreator
import fr.tokazio.archivald.konsist.assertFalse

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
