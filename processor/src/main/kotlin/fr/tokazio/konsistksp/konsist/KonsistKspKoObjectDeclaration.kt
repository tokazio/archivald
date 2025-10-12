package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.*
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.ClassDeclaration
import kotlin.reflect.KClass

class KonsistKspKoObjectDeclaration(
    private val logger: Logger,
    private val inner: ClassDeclaration,
) : KoObjectDeclaration {
    override val fullyQualifiedName: String?
        get() = TODO("Not yet implemented")

    override fun hasNameContaining(text: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasNameEndingWith(suffix: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasNameMatching(regex: Regex): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasNameStartingWith(prefix: String): Boolean {
        TODO("Not yet implemented")
    }

    override val name: String
        get() = TODO("Not yet implemented")

    override val packagee: KoPackageDeclaration
        get() = TODO("Not yet implemented")

    override fun resideInPackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideOutsidePackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTextContaining(str: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTextEndingWith(suffix: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTextMatching(regex: Regex): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTextStartingWith(prefix: String): Boolean {
        TODO("Not yet implemented")
    }

    override val text: String
        get() = TODO("Not yet implemented")

    override fun countAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllAnnotationsOf(names: Collection<KClass<*>>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllAnnotationsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAnnotation(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAnnotationOf(names: Collection<KClass<*>>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAnnotationOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAnnotationWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAnnotationWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAnnotations(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAnnotationsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAnnotationsWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override val annotations: List<KoAnnotationDeclaration>
        get() = TODO("Not yet implemented")
    override val numAnnotations: Int
        get() = TODO("Not yet implemented")

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
        get() = TODO("Not yet implemented")
    override val containingFile: KoFileDeclaration
        get() = TODO("Not yet implemented")

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
    ): List<KoBaseDeclaration> {
        TODO("Not yet implemented")
    }

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

    override val kDoc: KoKDocDeclaration
        get() = TODO("Not yet implemented")

    override val location: String
        get() = TODO("Not yet implemented")

    override val locationWithText: String
        get() = TODO("Not yet implemented")

    override fun hasAllModifiers(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean = hasAllModifiers(listOf(modifier, *modifiers))

    override fun hasAllModifiers(modifiers: Collection<KoModifier>): Boolean =
        when {
            modifiers.isEmpty() -> hasModifiers()
            else -> this.modifiers.containsAll(modifiers)
        }

    override fun hasModifier(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean = hasModifier(listOf(modifier, *modifiers))

    override fun hasModifier(modifiers: Collection<KoModifier>): Boolean =
        when {
            modifiers.isEmpty() -> hasModifiers()
            else -> modifiers.any { this.modifiers.any { modifier -> modifier == it } }
        }

    override fun hasModifiers(): Boolean = modifiers.isNotEmpty()

    override val modifiers: List<KoModifier> by lazy {
        inner.modifiers.map {
            KoModifier.valueOf(it.name)
        }
    }

    override val numModifiers: Int = modifiers.size

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

    override fun objects(includeNested: Boolean): List<KoObjectDeclaration> {
        TODO("Not yet implemented")
    }

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

    override fun properties(includeNested: Boolean): List<KoPropertyDeclaration> {
        TODO("Not yet implemented")
    }

    override fun representsType(name: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideInSourceSet(sourceSetName: String): Boolean {
        TODO("Not yet implemented")
    }

    override val sourceSetName: String
        get() = TODO("Not yet implemented")
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

    override val parentClass: KoParentDeclaration
        get() = TODO("Not yet implemented")

    override val hasCompanionModifier: Boolean
        get() = TODO("Not yet implemented")

    override fun toString(): String = inner.toString()
}
