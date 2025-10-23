package fr.tokazio.archivald.konsist.bridge

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.*
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import fr.tokazio.archivald.internal.logger.Logger
import fr.tokazio.archivald.internal.model.FunctionDeclaration
import fr.tokazio.archivald.internal.model.ValueParameter
import fr.tokazio.archivald.konsist.bridge.provider.KonsistKspKoAnnotationProvider
import fr.tokazio.archivald.konsist.bridge.provider.KonsistKspKoModifierProvider
import fr.tokazio.archivald.konsist.bridge.provider.KonsistKspKoNameProvider
import fr.tokazio.archivald.konsist.bridge.provider.KonsistKspKoTextProvider
import kotlin.reflect.KClass

class KonsistKspKoParameterDeclaration(
    private val logger: Logger,
    val valueParameter: ValueParameter,
) : KoParameterDeclaration,
    KonsistKspKoAnnotationProvider,
    KonsistKspKoModifierProvider,
    KonsistKspKoNameProvider,
    KonsistKspKoTextProvider {
    override val name: String = valueParameter.name

    override val location: String = valueParameter.location.asString()

    override val annotations: List<KoAnnotationDeclaration> by lazy {
        valueParameter.annotations
            .map {
                KonsistKspKoAnnotationDeclaration(logger, it)
            }.toList()
    }

    override val numAnnotations: Int by lazy { annotations.size }

    // No modifiers on function parameters
    override val modifiers: List<KoModifier> = emptyList()

    override val containingDeclaration: KoBaseDeclaration =
        when (valueParameter.containingDeclaration) {
            is FunctionDeclaration ->
                KonsistKspKoFunctionDeclaration(
                    logger,
                    valueParameter.containingDeclaration as FunctionDeclaration,
                )

            else -> throw IllegalStateException(
                "Unhandled koParameter containingDeclaration ${valueParameter.containingDeclaration::class.java.simpleName}",
            )
        }

    override val numModifiers: Int by lazy { modifiers.size }

    override val text: String = "KonsistKspKoParameterDeclaration?"

    override fun toString(): String = valueParameter.toString()

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override val containingFile: KoFileDeclaration
        get() = TODO("Not yet implemented")

    override fun hasDefaultValue(value: String?): Boolean {
        TODO("Not yet implemented")
    }

    override val defaultValue: String?
        get() = TODO("Not yet implemented")
    override val locationWithText: String
        get() = TODO("Not yet implemented")

    override val packagee: KoPackageDeclaration?
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

    override fun representsType(name: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideInPackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideOutsidePackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasType(predicate: (KoTypeDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val type: KoTypeDeclaration
        get() = TODO("Not yet implemented")
    override val hasVarModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasValModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasVarArgModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasNoInlineModifier: Boolean
        get() = TODO("Not yet implemented")
    override val hasCrossInlineModifier: Boolean
        get() = TODO("Not yet implemented")
    override val isVal: Boolean
        get() = TODO("Not yet implemented")
    override val isVar: Boolean
        get() = TODO("Not yet implemented")
}
