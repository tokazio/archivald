package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.*
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import fr.tokazio.konsistksp.api.ClassDeclaration
import fr.tokazio.konsistksp.api.Logger
import kotlin.reflect.KClass

class KonsistKspKoClassDeclaration(
    private val logger: Logger,
    val inner: ClassDeclaration,
) : KoClassDeclaration {
    override fun toString(): String = inner.toString()

    override val fullyQualifiedName: String = inner.qualifiedName

    override fun hasNameContaining(text: String): Boolean = fullyQualifiedName.contains(text)

    override fun hasNameEndingWith(suffix: String): Boolean = fullyQualifiedName.endsWith(suffix)

    override fun hasNameMatching(regex: Regex): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasNameStartingWith(prefix: String): Boolean = fullyQualifiedName.startsWith(prefix)

    override val name: String = inner.qualifiedName

    override val packagee: KoPackageDeclaration = KonsistKspKoPackageDeclaration(logger, inner)

    override fun resideInPackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideOutsidePackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTextContaining(str: String): Boolean = text.contains(str)

    override fun hasTextEndingWith(suffix: String): Boolean = text.endsWith(suffix)

    override fun hasTextMatching(regex: Regex): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTextStartingWith(prefix: String): Boolean = text.startsWith(prefix)

    override val text: String
        get() = TODO("Not yet implemented")

    override fun countAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Int = annotations.size

    override fun hasAllAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean = annotations.all(predicate)

    override fun hasAllAnnotationsOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else -> names.all { checkIfAnnotated(it) }
        }

    override fun hasAllAnnotationsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasAllAnnotationsOf(listOf(name, *names))

    override fun hasAnnotation(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean = annotations.any(predicate)

    override fun hasAnnotationOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else -> names.any { checkIfAnnotated(it) }
        }

    override fun hasAnnotationOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasAnnotationOf(listOf(name, *names))

    override fun hasAnnotationWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasAnnotationWithName(listOf(name, *names))

    override fun hasAnnotationWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else ->
                names.any {
                    annotations.any { annotation -> annotation.representsType(it) }
                }
        }

    override fun hasAnnotations(): Boolean = annotations.isNotEmpty()

    override fun hasAnnotationsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasAnnotationsWithAllNames(listOf(name, *names))

    override fun hasAnnotationsWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else ->
                names.all {
                    annotations.any { annotation -> annotation.representsType(it) }
                }
        }

    override val annotations: List<KoAnnotationDeclaration> by lazy {
        inner.annotations
            .map {
                KonsistKspKoAnnotationDeclaration(logger, it)
            }.toList()
    }

    override val numAnnotations: Int = annotations.size

    override fun classesAndInterfacesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassAndInterfaceAndObjectDeclaration> {
        TODO("Not yet implemented")
    }

    override fun countClassesAndInterfacesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllClassesAndInterfacesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterfaceOrObject(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterfaceOrObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterfaceOrObjectWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesAndInterfacesAndObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesAndInterfacesAndObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesOrInterfacesOrObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numClassesAndInterfacesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun classesAndInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassAndInterfaceDeclaration> {
        TODO("Not yet implemented")
    }

    override fun countClassesAndInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllClassesAndInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterface(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterfaceWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterfaceWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesAndInterfacesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesAndInterfacesWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesOrInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numClassesAndInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun classesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassAndObjectDeclaration> {
        TODO("Not yet implemented")
    }

    override fun countClassesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndObjectDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllClassesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndObjectDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrObject(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndObjectDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrObjectWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesAndObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesAndObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesOrObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numClassesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun countInterfacesAndObjects(
        includeNested: Boolean,
        predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllInterfacesAndObjects(
        includeNested: Boolean,
        predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaceOrObject(
        includeNested: Boolean,
        predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaceOrObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaceOrObjectWithName(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfacesAndObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfacesAndObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfacesOrObjects(includeNested: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun interfacesAndObjects(includeNested: Boolean): List<KoInterfaceAndObjectDeclaration> {
        TODO("Not yet implemented")
    }

    override fun numInterfacesAndObjects(includeNested: Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassDeclaration> {
        TODO("Not yet implemented")
    }

    override fun countClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClass(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassesWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override val containingDeclaration: KoBaseDeclaration
        get() = KonsistKspKoDeclaration(logger, inner.containingFile)

    override val containingFile: KoFileDeclaration
        get() = KonsistKspKoFileDeclaration(logger, inner.containingFile)

    override fun countDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> =
        inner.declarations
            .map {
                KonsistKspKoDeclaration(logger, it)
            }.toList()

    override fun hasAllDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasDeclaration(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun numInternalDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun numPrivateDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun numProtectedDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun numPublicDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun numPublicOrDefaultDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun countExternalParents(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun externalParents(indirectParents: Boolean): List<KoParentDeclaration> {
        TODO("Not yet implemented")
    }

    override fun hasAllExternalParents(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllExternalParentsOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllExternalParentsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalParent(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalParentOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalParentOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalParentWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalParentWithName(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalParents(indirectParents: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalParentsWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalParentsWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numExternalParents(indirectParents: Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun countFunctions(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun functions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoFunctionDeclaration> {
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

    override fun countInterfaces(
        includeNested: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllInterfaces(
        includeNested: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterface(
        includeNested: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaceWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaceWithName(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaces(includeNested: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfacesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfacesWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun interfaces(includeNested: Boolean): List<KoInterfaceDeclaration> {
        TODO("Not yet implemented")
    }

    override fun numInterfaces(includeNested: Boolean): Int {
        TODO("Not yet implemented")
    }

    override val hasKDoc: Boolean
        get() = TODO("Not yet implemented")

    override val kDoc: KoKDocDeclaration?
        get() = TODO("Not yet implemented")

    override val location: String = inner.containingFile.filePath

    override val locationWithText: String
        get() = TODO("Not yet implemented")

    override fun hasAllModifiers(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllModifiers(modifiers: Collection<KoModifier>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasModifier(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasModifier(modifiers: Collection<KoModifier>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasModifiers(): Boolean {
        TODO("Not yet implemented")
    }

    override val modifiers: List<KoModifier>
        get() = TODO("Not yet implemented")
    override val numModifiers: Int
        get() = TODO("Not yet implemented")

    override fun resideInModule(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override val moduleName: String
        get() = TODO("Not yet implemented")

    override fun countObjects(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllObjects(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasObject(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasObjectWithName(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasObjects(includeNested: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numObjects(includeNested: Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun objects(includeNested: Boolean): List<KoObjectDeclaration> =
        inner.declarations
            .filterIsInstance<ClassDeclaration>()
            .filter {
                it.isObject || it.isCompanionObject
            }.map {
                KonsistKspKoObjectDeclaration(it)
            }.toList()

    override fun countParentInterfaces(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllParentInterfaces(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllParentInterfacesOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllParentInterfacesOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentInterface(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentInterfaceOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentInterfaceOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentInterfaceWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentInterfaceWithName(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentInterfaces(indirectParents: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentInterfacesWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentInterfacesWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numParentInterfaces(indirectParents: Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun parentInterfaces(indirectParents: Boolean): List<KoParentDeclaration> {
        TODO("Not yet implemented")
    }

    override fun countParents(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllParents(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllParentsOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllParentsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParent(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentWithName(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParents(indirectParents: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentsWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentsWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numParents(indirectParents: Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun parents(indirectParents: Boolean): List<KoParentDeclaration> {
        TODO("Not yet implemented")
    }

    override fun resideInPath(
        path: String,
        absolutePath: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override val path: String
        get() = TODO("Not yet implemented")
    override val projectPath: String
        get() = TODO("Not yet implemented")

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

    override fun numProperties(includeNested: Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun properties(includeNested: Boolean): List<KoPropertyDeclaration> = emptyList() // TODO

    override fun representsType(name: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideInSourceSet(sourceSetName: String): Boolean {
        TODO("Not yet implemented")
    }

    override val sourceSetName: String
        get() = inner.sourceSetName

    override val isTopLevel: Boolean
        get() = TODO("Not yet implemented")
    override val hasInternalModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasPrivateModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasProtectedModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasPublicModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasPublicOrDefaultModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasActualModifier: Boolean
        get() = TODO("Not yet implemented")

    override fun children(indirectChildren: Boolean): List<KoChildDeclaration> {
        TODO("Not yet implemented")
    }

    override fun countChildren(
        indirectChildren: Boolean,
        predicate: (KoChildDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllChildren(
        indirectChildren: Boolean,
        predicate: (KoChildDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllChildrenOf(
        names: Collection<KClass<*>>,
        indirectChildren: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllChildrenOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectChildren: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasChild(
        indirectChildren: Boolean,
        predicate: (KoChildDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasChildOf(
        names: Collection<KClass<*>>,
        indirectChildren: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasChildOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectChildren: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasChildWithName(
        name: String,
        vararg names: String,
        indirectChildren: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasChildWithName(
        names: Collection<String>,
        indirectChildren: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasChildren(indirectChildren: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasChildrenWithAllNames(
        name: String,
        vararg names: String,
        indirectChildren: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasChildrenWithAllNames(
        names: Collection<String>,
        indirectChildren: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numChildren(indirectChildren: Boolean): Int {
        TODO("Not yet implemented")
    }

    override val hasExpectModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasSealedModifier: Boolean
        get() = TODO("Not yet implemented")

    override fun countTypeParameters(predicate: (KoTypeParameterDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllTypeParameters(predicate: (KoTypeParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeParameter(predicate: (KoTypeParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeParameterWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeParameterWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeParameters(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeParametersWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeParametersWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override val numTypeParameters: Int
        get() = TODO("Not yet implemented")
    override val typeParameters: List<KoTypeParameterDeclaration>
        get() = TODO("Not yet implemented")
    override val isGeneric: Boolean
        get() = TODO("Not yet implemented")
    override val hasDataModifier: Boolean
        get() = TODO("Not yet implemented")

    override fun countInitBlocks(predicate: (KoInitBlockDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllInitBlocks(predicate: (KoInitBlockDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInitBlock(predicate: (KoInitBlockDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInitBlocks(): Boolean {
        TODO("Not yet implemented")
    }

    override val initBlocks: List<KoInitBlockDeclaration>
        get() = TODO("Not yet implemented")
    override val numInitBlocks: Int
        get() = TODO("Not yet implemented")

    override fun countParentClasses(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllParentClasses(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllParentClassesOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllParentClassesOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentClass(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentClass(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentClassOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentClassOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentClassWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentClassWithName(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentClasses(indirectParents: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentClassesWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParentClassesWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numParentClasses(indirectParents: Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun parentClasses(indirectParents: Boolean): List<KoParentDeclaration> {
        TODO("Not yet implemented")
    }

    override val parentClass: KoParentDeclaration?
        get() = TODO("Not yet implemented")
    override val hasAbstractModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasAnnotationModifier: Boolean
        get() = TODO("Not yet implemented")

    override fun countConstructors(predicate: (KoConstructorDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllConstructors(predicate: (KoConstructorDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasConstructor(predicate: (KoConstructorDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasConstructors(): Boolean {
        TODO("Not yet implemented")
    }

    override val constructors: List<KoConstructorDeclaration>
        get() = TODO("Not yet implemented")
    override val numConstructors: Int
        get() = TODO("Not yet implemented")

    override fun countEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasEnumConstant(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasEnumConstantWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasEnumConstantWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasEnumConstants(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasEnumConstantsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasEnumConstantsWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override val enumConstants: List<KoEnumConstantDeclaration>
        get() = TODO("Not yet implemented")
    override val numEnumConstants: Int
        get() = TODO("Not yet implemented")
    override val hasEnumModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasFinalModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasInnerModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasOpenModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasPrimaryConstructor: Boolean
        get() = TODO("Not yet implemented")
    override val primaryConstructor: KoPrimaryConstructorDeclaration?
        get() = TODO("Not yet implemented")

    override fun countSecondaryConstructors(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllSecondaryConstructors(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasSecondaryConstructor(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasSecondaryConstructors(): Boolean {
        TODO("Not yet implemented")
    }

    override val numSecondaryConstructors: Int
        get() = TODO("Not yet implemented")
    override val secondaryConstructors: List<KoSecondaryConstructorDeclaration>
        get() = TODO("Not yet implemented")

    override fun countTestClasses(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun hasTestClass(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTestClasses(
        testPropertyName: String,
        moduleName: String?,
        sourceSetName: String?,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun numTestClasses(
        testPropertyName: String,
        moduleName: String?,
        sourceSetName: String?,
    ): Int {
        TODO("Not yet implemented")
    }

    override fun testClasses(
        testPropertyName: String,
        moduleName: String?,
        sourceSetName: String?,
    ): List<KoClassDeclaration> {
        TODO("Not yet implemented")
    }

    override fun testClasses(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): List<KoClassDeclaration> {
        TODO("Not yet implemented")
    }

    override val hasValueModifier: Boolean
        get() = TODO("Not yet implemented")

    private fun checkIfAnnotated(kClass: KClass<*>): Boolean =
        annotations.any { annotation ->
            if (kClass.qualifiedName?.startsWith("kotlin.") == true) {
                annotation.name == kClass.simpleName
            } else {
                annotation.fullyQualifiedName == kClass.qualifiedName
            }
        }
}
