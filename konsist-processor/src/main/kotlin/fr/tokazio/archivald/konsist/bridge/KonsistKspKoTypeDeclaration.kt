package fr.tokazio.archivald.konsist.bridge

import com.lemonappdev.konsist.api.declaration.*
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import fr.tokazio.archivald.internal.logger.Logger
import fr.tokazio.archivald.internal.model.TypeReference
import fr.tokazio.archivald.konsist.bridge.provider.KonsistKspKoNameProvider
import fr.tokazio.archivald.konsist.bridge.provider.KonsistKspKoTextProvider
import kotlin.reflect.KClass

class KonsistKspKoTypeDeclaration(
    private val logger: Logger,
    val typeReference: TypeReference,
) : KoTypeDeclaration,
    KonsistKspKoNameProvider,
    KonsistKspKoTextProvider {
    override val name: String = typeReference.name

    override val text: String = "KonsistKspKoTypeDeclaration?"

    override fun toString(): String = typeReference.toString()

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override fun asClassDeclaration(): KoClassDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asClassOrInterfaceDeclaration(): KoClassAndInterfaceDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asClassOrInterfaceOrObjectDeclaration(): KoClassAndInterfaceAndObjectDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asClassOrObjectDeclaration(): KoClassAndObjectDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asExternalDeclaration(): KoExternalDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asFunctionDeclaration(): KoFunctionDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asImportAliasDeclaration(): KoImportAliasDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asInterfaceDeclaration(): KoInterfaceDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asInterfaceOrObjectDeclaration(): KoInterfaceAndObjectDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asKotlinBasicTypeDeclaration(): KoKotlinTypeDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asKotlinCollectionTypeDeclaration(): KoKotlinTypeDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asObjectDeclaration(): KoObjectDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asPropertyDeclaration(): KoPropertyDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asTypeAliasDeclaration(): KoTypeAliasDeclaration? {
        TODO("Not yet implemented")
    }

    override fun asTypeParameterDeclaration(): KoTypeParameterDeclaration? {
        TODO("Not yet implemented")
    }

    override fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterfaceDeclaration(predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterfaceDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterfaceOrObjectDeclaration(predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrObjectDeclaration(predicate: ((KoClassAndObjectDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasClassOrObjectDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasFunctionDeclaration(predicate: ((KoFunctionDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasFunctionDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportAliasDeclaration(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaceDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaceOrObjectDeclaration(predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasKotlinBasicTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasKotlinBasicTypeDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasKotlinCollectionTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasKotlinCollectionTypeDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasKotlinTypeDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasObjectDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPropertyDeclaration(predicate: ((KoPropertyDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPropertyDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override val isClass: Boolean
        get() = TODO("Not yet implemented")
    override val isClassOrInterface: Boolean
        get() = TODO("Not yet implemented")
    override val isClassOrInterfaceOrObject: Boolean
        get() = TODO("Not yet implemented")
    override val isClassOrObject: Boolean
        get() = TODO("Not yet implemented")
    override val isExternal: Boolean
        get() = TODO("Not yet implemented")
    override val isFunction: Boolean
        get() = TODO("Not yet implemented")
    override val isImportAlias: Boolean
        get() = TODO("Not yet implemented")
    override val isInterface: Boolean
        get() = TODO("Not yet implemented")
    override val isInterfaceOrObject: Boolean
        get() = TODO("Not yet implemented")
    override val isKotlinBasicType: Boolean
        get() = TODO("Not yet implemented")
    override val isKotlinCollectionType: Boolean
        get() = TODO("Not yet implemented")
    override val isKotlinType: Boolean
        get() = TODO("Not yet implemented")
    override val isObject: Boolean
        get() = TODO("Not yet implemented")
    override val isProperty: Boolean
        get() = TODO("Not yet implemented")
    override val isTypeAlias: Boolean
        get() = TODO("Not yet implemented")
    override val isTypeParameter: Boolean
        get() = TODO("Not yet implemented")

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
    override val location: String
        get() = TODO("Not yet implemented")
    override val locationWithText: String
        get() = TODO("Not yet implemented")
    override val isNullable: Boolean
        get() = TODO("Not yet implemented")
    override val containingFile: KoFileDeclaration
        get() = TODO("Not yet implemented")
    override val containingDeclaration: KoBaseDeclaration
        get() = TODO("Not yet implemented")

    override fun resideInModule(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override val moduleName: String
        get() = TODO("Not yet implemented")

    override fun resideInSourceSet(sourceSetName: String): Boolean {
        TODO("Not yet implemented")
    }

    override val sourceSetName: String
        get() = TODO("Not yet implemented")
    override val isGenericType: Boolean
        get() = TODO("Not yet implemented")
    override val isGeneric: Boolean
        get() = TODO("Not yet implemented")
    override val isFunctionType: Boolean
        get() = TODO("Not yet implemented")
    override val bareSourceType: String
        get() = TODO("Not yet implemented")
    override val isAlias: Boolean
        get() = TODO("Not yet implemented")
    override val sourceType: String
        get() = TODO("Not yet implemented")
    override val packagee: KoPackageDeclaration?
        get() = TODO("Not yet implemented")

    override fun resideInPackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideOutsidePackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

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

    override fun hasSourceDeclaration(predicate: (KoDeclarationCastProvider) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val sourceDeclaration: KoDeclarationCastProvider?
        get() = TODO("Not yet implemented")
    override val isMutableType: Boolean
        get() = TODO("Not yet implemented")

    override fun countTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllTypeArgumentsOf(names: Collection<KClass<*>>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllTypeArgumentsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeArgument(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeArgumentOf(names: Collection<KClass<*>>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeArgumentOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeArgumentWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeArgumentWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeArguments(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeArgumentsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeArgumentsWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override val numTypeArguments: Int
        get() = TODO("Not yet implemented")
    override val typeArguments: List<KoTypeArgumentDeclaration>?
        get() = TODO("Not yet implemented")

    override fun countParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun countParameters(predicate: (KoParameterDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasAllParameters(predicate: (KoParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameter(predicate: (KoParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameterType(predicate: (KoParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasReturnType(predicate: (KoTypeDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasReturnTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val numParameterTypes: Int
        get() = TODO("Not yet implemented")
    override val numParameters: Int
        get() = TODO("Not yet implemented")
    override val parameterTypes: List<KoParameterDeclaration>?
        get() = TODO("Not yet implemented")
    override val parameters: List<KoParameterDeclaration>?
        get() = TODO("Not yet implemented")
    override val returnType: KoTypeDeclaration?
        get() = TODO("Not yet implemented")

    override fun asExternalTypeDeclaration(): KoExternalDeclaration? {
        TODO("Not yet implemented")
    }

    override fun hasExternalTypeDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasExternalTypeDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val isExternalType: Boolean
        get() = TODO("Not yet implemented")
    override val isStarProjection: Boolean
        get() = TODO("Not yet implemented")
}
