package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.*
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.ClassDeclaration
import fr.tokazio.konsistksp.internal.model.FunctionDeclaration
import fr.tokazio.konsistksp.konsist.provider.*
import java.util.*
import kotlin.reflect.KClass

class KonsistKspKoFunctionDeclaration(
    private val logger: Logger,
    val functionDeclaration: FunctionDeclaration,
) : KoFunctionDeclaration,
    KonsistKspKoNameProvider,
    KonsistKspKoTextProvider,
    KonsistKspKoAnnotationProvider,
    KonsistKspKoModifierProvider,
    KonsistKspKoReturnProvider,
    KonsistKspKoParameterProvider {
    override val name: String = functionDeclaration.simpleName

    override val fullyQualifiedName: String = functionDeclaration.qualifiedName

    override val text: String = "KonsistKspKoFunctionDeclaration?"

    override val annotations: List<KoAnnotationDeclaration> by lazy {
        functionDeclaration.annotations
            .map {
                KonsistKspKoAnnotationDeclaration(logger, it)
            }.toList()
    }

    override val numAnnotations: Int by lazy { annotations.size }

    override val returnType: KoTypeDeclaration? =
        functionDeclaration.returnType?.let { KonsistKspKoTypeDeclaration(logger, it) }

    override val hasReturnValue: Boolean = returnType != null

    override fun hasReturnType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean = hasReturnValue

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

    override val hasInternalModifier: Boolean by lazy {
        modifiers.contains(KoModifier.INTERNAL)
    }

    override val hasPrivateModifier: Boolean by lazy {
        modifiers.contains(KoModifier.PRIVATE)
    }

    override val hasProtectedModifier: Boolean by lazy {
        modifiers.contains(KoModifier.PROTECTED)
    }

    override val hasPublicModifier: Boolean by lazy {
        modifiers.contains(KoModifier.PUBLIC)
    }

    override val hasPublicOrDefaultModifier: Boolean by lazy {
        modifiers.none {
            EnumSet
                .of(KoModifier.PRIVATE, KoModifier.PROTECTED, KoModifier.INTERNAL)
                .contains(it)
        }
    }

    override val hasOperatorModifier: Boolean by lazy {
        modifiers.contains(KoModifier.OPERATOR)
    }

    override val hasInlineModifier: Boolean by lazy {
        modifiers.contains(KoModifier.INLINE)
    }

    override val hasTailrecModifier: Boolean by lazy {
        modifiers.contains(KoModifier.TAILREC)
    }

    override val hasInfixModifier: Boolean by lazy {
        modifiers.contains(KoModifier.INFIX)
    }

    override val hasExternalModifier: Boolean by lazy {
        modifiers.contains(KoModifier.INLINE)
    }

    override val hasSuspendModifier: Boolean by lazy {
        modifiers.contains(KoModifier.SUSPEND)
    }

    override val hasOpenModifier: Boolean by lazy {
        modifiers.contains(KoModifier.OPEN)
    }

    override val hasOverrideModifier: Boolean by lazy {
        modifiers.contains(KoModifier.OVERRIDE)
    }

    override val hasFinalModifier: Boolean by lazy {
        modifiers.contains(KoModifier.FINAL)
    }

    override val hasAbstractModifier: Boolean by lazy {
        modifiers.contains(KoModifier.ABSTRACT)
    }

    override val hasActualModifier: Boolean by lazy {
        modifiers.contains(KoModifier.ACTUAL)
    }

    override val hasExpectModifier: Boolean by lazy {
        modifiers.contains(KoModifier.EXPECT)
    }

    override val containingDeclaration: KoBaseDeclaration =
        when (functionDeclaration.containingDeclaration) {
            is ClassDeclaration ->
                KonsistKspKoClassDeclaration(
                    logger,
                    functionDeclaration.containingDeclaration as ClassDeclaration,
                )

            else -> throw IllegalStateException(
                "Unhandled koFunction containingDeclaration ${functionDeclaration.containingDeclaration::class.java.simpleName}",
            )
        }

    override val numModifiers: Int by lazy { modifiers.size }

    override val numParameters: Int by lazy { parameters.size }

    override val parameters: List<KoParameterDeclaration> by lazy {
        functionDeclaration.parameters.map {
            KonsistKspKoParameterDeclaration(logger, it)
        }
    }

    override fun toString(): String = functionDeclaration.toString()

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override fun hasReturnTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val isTopLevel: Boolean
        get() = TODO("Not yet implemented")

    override val hasBlockBody: Boolean
        get() = TODO("Not yet implemented")

    override val hasExpressionBody: Boolean
        get() = TODO("Not yet implemented")

    override val containingFile: KoFileDeclaration
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
