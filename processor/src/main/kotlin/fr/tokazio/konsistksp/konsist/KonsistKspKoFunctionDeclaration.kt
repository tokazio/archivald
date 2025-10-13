package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.*
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.FunctionDeclaration
import fr.tokazio.konsistksp.konsist.provider.*
import kotlin.reflect.KClass

class KonsistKspKoFunctionDeclaration(
    private val logger: Logger,
    val functionDeclaration: FunctionDeclaration,
) : KoFunctionDeclaration,
    KonsistKspKoNameProvider,
    KonsistKspKoTextProvider,
    KonsistKspKoAnnotationProvider,
    KonsistKspKoModifierProvider,
    KonsistKspKoReturnProvider {
    override val name: String = functionDeclaration.name

    override val text: String = "KonsistKspKoFunctionDeclaration?"

    override val annotations: List<KoAnnotationDeclaration> by lazy {
        functionDeclaration.annotations
            .map {
                KonsistKspKoAnnotationDeclaration(logger, it)
            }.toList()
    }

    override val numAnnotations: Int = annotations.size

    override val returnType: KoTypeDeclaration? =
        functionDeclaration.returnType?.let { KonsistKspKoTypeDeclaration(logger, it) }

    override val hasReturnValue: Boolean = returnType != null

    override fun hasReturnType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean = hasReturnValue

    override fun hasReturnTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val numVariables: Int by lazy {
        variables.size
    }

    override val variables: List<KoVariableDeclaration> by lazy {
        functionDeclaration.parameters.map {
            KonsistKspKoVariableDeclaration(logger, it)
        }
    }

    override val modifiers: List<KoModifier> by lazy {
        functionDeclaration.modifiers.map {
            KoModifier.valueOf(it.name)
        }
    }

    override val numModifiers: Int = modifiers.size

    override fun toString(): String = functionDeclaration.toString()

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override val hasBlockBody: Boolean
        get() = TODO("Not yet implemented")

    override val hasExpressionBody: Boolean
        get() = TODO("Not yet implemented")

    override val containingFile: KoFileDeclaration
        get() = TODO("Not yet implemented")

    override val fullyQualifiedName: String?
        get() = TODO("Not yet implemented")

    override val isInitialized: Boolean
        get() = TODO("Not yet implemented")

    override val hasKDoc: Boolean
        get() = TODO("Not yet implemented")

    override val kDoc: KoKDocDeclaration?
        get() = TODO("Not yet implemented")

    override val localClasses: List<KoClassDeclaration>
        get() = TODO("Not yet implemented")

    override val numLocalClasses: Int
        get() = TODO("Not yet implemented")

    override fun countLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalClass(predicate: (KoClassDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalClassWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalClassWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalClasses(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalClassesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalClassesWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override val localDeclarations: List<KoBaseDeclaration>
        get() = TODO("Not yet implemented")

    override val numLocalDeclarations: Int
        get() = TODO("Not yet implemented")

    override fun countLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalDeclarations(): Boolean {
        TODO("Not yet implemented")
    }

    override val localFunctions: List<KoFunctionDeclaration> = emptyList() // TODO

    override val numLocalFunctions: Int = localFunctions.size

    override fun countLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Int = numLocalFunctions

    override fun hasAllLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalFunctionWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalFunctionWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalFunctions(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalFunctionsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasLocalFunctionsWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun countVariables(predicate: (KoVariableDeclaration) -> Boolean): Int = numVariables

    override fun hasAllVariables(predicate: (KoVariableDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasVariable(predicate: (KoVariableDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasVariableWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasVariableWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasVariables(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasVariablesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasVariablesWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override val location: String
        get() = TODO("Not yet implemented")

    override val locationWithText: String
        get() = TODO("Not yet implemented")

    override val packagee: KoPackageDeclaration?
        get() = TODO("Not yet implemented")

    override val numParameters: Int
        get() = TODO("Not yet implemented")

    override val parameters: List<KoParameterDeclaration>
        get() = TODO("Not yet implemented")

    override fun countParameters(predicate: (KoParameterDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllParameters(predicate: (KoParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameter(predicate: (KoParameterDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameterWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameterWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParameters(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParametersWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasParametersWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override val containingDeclaration: KoBaseDeclaration
        get() = TODO("Not yet implemented")

    override val path: String
        get() = TODO("Not yet implemented")

    override val projectPath: String
        get() = TODO("Not yet implemented")

    override fun resideInPath(
        path: String,
        absolutePath: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override val moduleName: String
        get() = TODO("Not yet implemented")

    override fun resideInModule(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override val sourceSetName: String
        get() = TODO("Not yet implemented")

    override fun resideInSourceSet(sourceSetName: String): Boolean {
        TODO("Not yet implemented")
    }

    override val receiverType: KoTypeDeclaration?
        get() = TODO("Not yet implemented")

    override fun hasReceiverType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasReceiverTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideInPackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideOutsidePackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

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

    override val hasOperatorModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasInlineModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasTailrecModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasInfixModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasExternalModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasSuspendModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasOpenModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasOverrideModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasFinalModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasAbstractModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasActualModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasExpectModifier: Boolean
        get() = TODO("Not yet implemented")
    override val numTypeParameters: Int
        get() = TODO("Not yet implemented")
    override val typeParameters: List<KoTypeParameterDeclaration>
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

    override val isGeneric: Boolean
        get() = TODO("Not yet implemented")

    override val isExtension: Boolean
        get() = TODO("Not yet implemented")
}
