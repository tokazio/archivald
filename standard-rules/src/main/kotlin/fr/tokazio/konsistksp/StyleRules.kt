package fr.tokazio.konsistksp

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.ext.list.indexOfFirstInstance
import com.lemonappdev.konsist.api.ext.list.indexOfLastInstance
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.provider.hasAnnotationOf
import org.springframework.beans.factory.annotation.Autowired
import javax.inject.Inject

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
                    .lastOrNull { obj ->
                        obj.hasModifier(KoModifier.COMPANION)
                    }.assertTrue { lastObject ->
                        classDeclaration
                            .declarations(includeNested = false, includeLocal = false)
                            .last() == lastObject
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

    @ArchitectureRule("No field injection", uuid = "QUKZwX")
    fun noClassShouldUseFieldInjection(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .classes()
            .filter {
                it.sourceSetName == "main"
            }.properties()
            .assertFalse {
                it.hasAnnotationOf<Inject>() || it.hasAnnotationOf<Autowired>()
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
