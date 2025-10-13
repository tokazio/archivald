package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.util.PathUtil.separator
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.ClassDeclaration
import fr.tokazio.konsistksp.konsist.provider.KonsistKspKoNameProvider
import fr.tokazio.konsistksp.konsist.provider.KonsistKspKoTextProvider

class KonsistKspKoPackageDeclaration(
    private val logger: Logger,
    val classDeclaration: ClassDeclaration,
) : KoPackageDeclaration,
    KonsistKspKoNameProvider,
    KonsistKspKoTextProvider {
    override val name: String = classDeclaration.packageName

    override val path: String = classDeclaration.containingFile.filePath

    override val containingFile: KoFileDeclaration =
        KonsistKspKoFileDeclaration(logger, classDeclaration.containingFile)

    override val hasMatchingPath: Boolean =
        path
            .replace(separator, ".")
            .endsWith(name + "." + containingFile.nameWithExtension)

    override val text: String = "KonsistKspKoPackageDeclaration?"

    override fun toString(): String = classDeclaration.toString()

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override val location: String
        get() = TODO("Not yet implemented")

    override val locationWithText: String
        get() = TODO("Not yet implemented")

    override fun resideInPath(
        path: String,
        absolutePath: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

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
}
