package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.*
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.PropertyDeclaration
import fr.tokazio.konsistksp.konsist.provider.KonsistKspKoAnnotationProvider
import fr.tokazio.konsistksp.konsist.provider.KonsistKspKoModifierProvider
import fr.tokazio.konsistksp.konsist.provider.KonsistKspKoNameProvider
import fr.tokazio.konsistksp.konsist.provider.KonsistKspKoTextProvider
import kotlin.reflect.KClass

class KonsistKspKoPropertyDeclaration(
    private val logger: Logger,
    val propertyDeclaration: PropertyDeclaration,
) : KoPropertyDeclaration,
    KonsistKspKoNameProvider,
    KonsistKspKoTextProvider,
    KonsistKspKoAnnotationProvider,
    KonsistKspKoModifierProvider {
    override val name: String = propertyDeclaration.name

    override val text: String = "KonsistKspKoPropertyDeclaration?"

    override val annotations: List<KoAnnotationDeclaration> by lazy {
        propertyDeclaration.annotations
            .map {
                KonsistKspKoAnnotationDeclaration(logger, it)
            }.toList()
    }

    override val numAnnotations: Int = annotations.size

    override val modifiers: List<KoModifier> by lazy {
        propertyDeclaration.modifiers.map {
            KoModifier.valueOf(it.name)
        }
    }

    override val numModifiers: Int = modifiers.size

    override fun toString(): String = propertyDeclaration.toString()

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override val isConstructorDefined: Boolean
        get() = TODO("Not yet implemented")
    override val containingFile: KoFileDeclaration
        get() = TODO("Not yet implemented")

    override fun hasDelegate(delegateName: String?): Boolean {
        TODO("Not yet implemented")
    }

    override val delegateName: String?
        get() = TODO("Not yet implemented")

    override fun hasType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val type: KoTypeDeclaration?
        get() = TODO("Not yet implemented")
    override val fullyQualifiedName: String?
        get() = TODO("Not yet implemented")
    override val isInitialized: Boolean
        get() = TODO("Not yet implemented")
    override val hasKDoc: Boolean
        get() = TODO("Not yet implemented")
    override val kDoc: KoKDocDeclaration?
        get() = TODO("Not yet implemented")
    override val location: String
        get() = TODO("Not yet implemented")
    override val locationWithText: String
        get() = TODO("Not yet implemented")

    override val packagee: KoPackageDeclaration?
        get() = TODO("Not yet implemented")
    override val containingDeclaration: KoBaseDeclaration
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

    override fun hasReceiverType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasReceiverTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val receiverType: KoTypeDeclaration?
        get() = TODO("Not yet implemented")

    override fun resideInPackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideOutsidePackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override val isTopLevel: Boolean
        get() = TODO("Not yet implemented")

    override fun hasValue(value: String?): Boolean {
        TODO("Not yet implemented")
    }

    override val value: String?
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
    override val hasValModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasVarModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasLateinitModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasOverrideModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasAbstractModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasOpenModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasFinalModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasActualModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasExpectModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasConstModifier: Boolean
        get() = TODO("Not yet implemented")
    override val getter: KoGetterDeclaration?
        get() = TODO("Not yet implemented")
    override val hasGetter: Boolean
        get() = TODO("Not yet implemented")
    override val hasSetter: Boolean
        get() = TODO("Not yet implemented")
    override val setter: KoSetterDeclaration?
        get() = TODO("Not yet implemented")
    override val isReadOnly: Boolean
        get() = TODO("Not yet implemented")

    override fun hasTacitType(type: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTacitTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

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
    override val isExtension: Boolean
        get() = TODO("Not yet implemented")
    override val isVal: Boolean
        get() = TODO("Not yet implemented")
    override val isVar: Boolean
        get() = TODO("Not yet implemented")
}
