package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.declaration.*
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.ValueParameter
import fr.tokazio.konsistksp.konsist.provider.KonsistKspKoAnnotationProvider
import kotlin.reflect.KClass

class KonsistKspKoVariableDeclaration(
    private val logger: Logger,
    val valueParameter: ValueParameter,
) : KoVariableDeclaration,
    KonsistKspKoAnnotationProvider {
    override val name: String = valueParameter.typeReference.name

    override val annotations: List<KoAnnotationDeclaration> by lazy {
        valueParameter.annotations
            .map {
                KonsistKspKoAnnotationDeclaration(logger, it)
            }.toList()
    }

    override val numAnnotations: Int by lazy {
        annotations.size
    }

    override fun toString(): String = valueParameter.toString()

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override val containingDeclaration: KoBaseDeclaration
        get() = TODO("Not yet implemented")

    override val containingFile: KoFileDeclaration
        get() = TODO("Not yet implemented")

    override fun hasDelegate(delegateName: String?): Boolean {
        TODO("Not yet implemented")
    }

    override val delegateName: String?
        get() = TODO("Not yet implemented")
    override val hasKDoc: Boolean
        get() = TODO("Not yet implemented")
    override val kDoc: KoKDocDeclaration?
        get() = TODO("Not yet implemented")
    override val location: String
        get() = TODO("Not yet implemented")
    override val locationWithText: String
        get() = TODO("Not yet implemented")

    override fun resideInModule(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override val moduleName: String
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

    override fun hasType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val type: KoTypeDeclaration?
        get() = TODO("Not yet implemented")

    override fun resideInPackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideOutsidePackage(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideInSourceSet(sourceSetName: String): Boolean {
        TODO("Not yet implemented")
    }

    override val sourceSetName: String
        get() = TODO("Not yet implemented")

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
    override val hasValModifier: Boolean
        get() = TODO("Not yet implemented")

    override fun hasValue(value: String?): Boolean {
        TODO("Not yet implemented")
    }

    override val value: String?
        get() = TODO("Not yet implemented")
    override val hasVarModifier: Boolean
        get() = TODO("Not yet implemented")

    override fun hasTacitType(type: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTacitTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override val isVal: Boolean
        get() = TODO("Not yet implemented")
    override val isVar: Boolean
        get() = TODO("Not yet implemented")
}
