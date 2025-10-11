package fr.tokazio.konsistksp

import com.lemonappdev.konsist.api.container.KoScopeCreator
import com.lemonappdev.konsist.api.verify.assertTrue

object LoggingRules {
    /**
     * Rule is:
     * do not use a logger from another class
     */
    @ArchitectureRule
    fun doNotUseALoggerFromAnotherClass(
        koScopeCreator: KoScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .files
            .filter { it.sourceSetName == "main" }
            .assertTrue {
                if (it.imports.isNotEmpty()) {
                    !it.hasImport { import ->
                        import.hasNameContaining("Companion.logger")
                    }
                } else {
                    true
                }
            }
    }

    /**
     * Rule is:
     * prefer WhozLogging insteadof KLogging
     */
    @ArchitectureRule
    fun preferWhozLoggingInsteadOfKLogging(
        koScopeCreator: KoScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .files
            .filter { it.sourceSetName == "main" }
            .assertTrue {
                if (it.imports.isNotEmpty()) {
                    !it.hasImport { import ->
                        import.hasNameContaining("mu.KLogging")
                    }
                } else {
                    true
                }
            }
    }
}
