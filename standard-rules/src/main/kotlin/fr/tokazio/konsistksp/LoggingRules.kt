package fr.tokazio.konsistksp

import com.lemonappdev.konsist.api.ext.list.imports

class LoggingRules {
    @ArchitectureRule("No java.util.logging", "62KpAK")
    fun noClassShouldUseJavaUtilLogging(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .files
            .assertFalse { it.hasImport { import -> import.name == "java.util.logging.." } }
    }

    @ArchitectureRule("Do not use a logger from another class", "KoSrE9")
    fun doNotUseALoggerFromAnotherClass(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .files
            .filter { it.sourceSetName == "main" }
            .imports
            .assertFalse {
                it.hasNameContaining("Companion.logger")
            }
    }

    @ArchitectureRule("Do not use mu.KLogging", "eGdogf")
    fun noMuKLogging(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .files
            .filter { it.sourceSetName == "main" }
            .imports
            .assertFalse {
                it.hasNameContaining("mu.KLogging")
            }
    }
}
