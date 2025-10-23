package fr.tokazio.archivald.konsist.bridge

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import fr.tokazio.archivald.internal.logger.Logger
import fr.tokazio.archivald.internal.model.ImportDeclaration
import fr.tokazio.archivald.konsist.bridge.provider.KonsistKspKoNameProvider
import fr.tokazio.archivald.konsist.bridge.provider.KonsistKspKoTextProvider
import kotlin.reflect.KClass

class KonsistKspKoImportDeclaration(
    private val logger: Logger,
    val konsistKspImport: ImportDeclaration,
    val importString: String,
) : KoImportDeclaration,
    KonsistKspKoNameProvider,
    KonsistKspKoTextProvider {
    override val text: String = "KonsistKspKoImportDeclaration?"

    override val isWildcard: Boolean = importString.endsWith("*")

    override val location: String = konsistKspImport.location.fullFilename

    override fun toString(): String = importString

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override val alias: KoImportAliasDeclaration
        get() = TODO("Not yet implemented")
    override val containingFile: KoFileDeclaration
        get() = TODO("Not yet implemented")
    override val locationWithText: String
        get() = TODO("Not yet implemented")
    override val moduleName: String
        get() = TODO("Not yet implemented")
    override val name: String = importString
    override val path: String
        get() = TODO("Not yet implemented")
    override val projectPath: String
        get() = TODO("Not yet implemented")
    override val sourceDeclaration: KoDeclarationCastProvider
        get() = TODO("Not yet implemented")
    override val sourceSetName: String
        get() = TODO("Not yet implemented")

    override fun hasAlias(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasSourceDeclaration(predicate: (KoDeclarationCastProvider) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }

    override fun matches(element: String): Boolean {
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
