package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.konsist.provider.KonsistKspKoNameProvider
import fr.tokazio.konsistksp.konsist.provider.KonsistKspKoTextProvider

class KonsistKspKoAnnotationDeclaration(
    private val logger: Logger,
    private val annotation: Annotation,
) : KoAnnotationDeclaration,
    KonsistKspKoNameProvider,
    KonsistKspKoTextProvider {
    override val fullyQualifiedName: String = annotation.fullyQualifiedName

    override val name: String = annotation.name

    override val text: String = "KonsistKspKoAnnotationDeclaration?"

    override fun toString(): String = annotation.toString()

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override val arguments: List<KoArgumentDeclaration>
        get() = TODO("Not yet implemented")

    override val containingFile: KoFileDeclaration
        get() = TODO("Not yet implemented")

    override val location: String
        get() = TODO("Not yet implemented")

    override val locationWithText: String
        get() = TODO("Not yet implemented")

    override val moduleName: String
        get() = TODO("Not yet implemented")

    override val numArguments: Int
        get() = TODO("Not yet implemented")

    override val path: String
        get() = TODO("Not yet implemented")

    override val projectPath: String
        get() = TODO("Not yet implemented")

    override val sourceSetName: String
        get() = TODO("Not yet implemented")

    override fun countArguments(predicate: (KoArgumentDeclaration) -> Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun hasAllArguments(predicate: (KoArgumentDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasArgument(predicate: (KoArgumentDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasArgumentWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasArgumentWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasArguments(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasArgumentsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasArgumentsWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun representsType(name: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideInModule(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideInPath(
        path: String,
        absolutePath: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun resideInSourceSet(sourceSetName: String): Boolean {
        TODO("Not yet implemented")
    }
}
