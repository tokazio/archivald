package fr.tokazio.konsistksp

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.ext.list.indexOfFirstInstance
import com.lemonappdev.konsist.api.ext.list.indexOfLastInstance
import com.lemonappdev.konsist.api.ext.list.properties

class StyleRules {
    @ArchitectureRule("Declare properties before function", uuid = "ghXnRF")
    fun propertiesAreDeclaredBeforeFunctions(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .classes()
            .filter { it.sourceSetName == "main" }
            .assertTrue {
                val lastKoPropertyDeclarationIndex =
                    it
                        .declarations(includeNested = false, includeLocal = false)
                        .indexOfLastInstance<KoPropertyDeclaration>()

                val firstKoFunctionDeclarationIndex =
                    it
                        .declarations(includeNested = false, includeLocal = false)
                        .indexOfFirstInstance<KoFunctionDeclaration>()

                if (lastKoPropertyDeclarationIndex != -1 && firstKoFunctionDeclarationIndex != -1) {
                    lastKoPropertyDeclarationIndex < firstKoFunctionDeclarationIndex
                } else {
                    true
                }
            }
    }

    @ArchitectureRule("Companion object is last declaration in the class", uuid = "Hq9wJG")
    fun companionObjectIsLastDeclarationInTheClass(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .classes()
            .forEach { classDeclaration ->
                classDeclaration
                    .objects(includeNested = false)
                    .firstOrNull { obj ->
                        obj.hasModifier(KoModifier.COMPANION)
                    }.assertTrue { companion ->
                        classDeclaration
                            .declarations(includeNested = false, includeLocal = false)
                            .lastOrNull() == companion
                    }
            }
    }

    @ArchitectureRule("No empty file allowed", uuid = "pABNWU")
    fun noEmptyFilesAllowed(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .files
            .assertFalse { it.text.isEmpty() }
    }

    @ArchitectureRule("No @Inject field injection", uuid = "QUKZwX")
    fun noClassShouldUseInjectedField(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .classes()
            .filter {
                it.sourceSetName == "main"
            }.properties()
            .forEach { propertyDeclaration ->
                propertyDeclaration.assertFalse {
                    it.hasAnnotationWithName("javax.inject.Inject")
                }
            }
    }

    @ArchitectureRule("No @Autowired field injection", uuid = "PaltsF")
    fun noClassShouldUseAutowiredField(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .classes()
            .filter {
                it.sourceSetName == "main"
            }.properties()
            .forEach { propertyDeclaration ->
                propertyDeclaration.assertFalse {
                    it.hasAnnotationWithName("org.springframework.beans.factory.annotation.Autowired")
                }
            }
    }

    @ArchitectureRule("Package name must match the file path", "G0cIEE")
    fun packageNameMustMatchFilePath(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .packages
            .assertTrue { it.hasMatchingPath }
    }
}
