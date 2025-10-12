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
    /**
     * Rule is:
     * properties are declared before functions
     */
    @ArchitectureRule
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

    /**
     * Rule is:
     * companion object is last declaration in the class
     */
    @ArchitectureRule
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

    /**
     * Rule is:
     * no empty files allowed
     */
    @ArchitectureRule
    fun noEmptyFilesAllowed(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .files
            .assertFalse { it.text.isEmpty() }
    }

    /**
     * Rule is:
     * no class should use field injection
     */
    @ArchitectureRule
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

    /**
     * Rule is:
     * no class should use Java util logging
     */
    @ArchitectureRule
    fun noClassShouldUseJavaUtilLogging(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .files
            .assertFalse { it.hasImport { import -> import.name == "java.util.logging.." } }
    }

    /**
     * Rule is:
     * package name must match file path
     */
    @ArchitectureRule
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
