package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.ksp.bridge.model.KonsistKspImport
import kotlin.reflect.KClass

class KonsistKspKoImportDeclaration(
    private val logger: Logger,
    val konsistKspImport: KonsistKspImport,
    val importString: String,
) : KoImportDeclaration {
    override val alias: KoImportAliasDeclaration
        get() = TODO("Not yet implemented")
    override val containingFile: KoFileDeclaration
        get() = TODO("Not yet implemented")
    override val isWildcard: Boolean
        get() = importString.endsWith("*")
    override val location: String
        get() = konsistKspImport.location.filePath
    override val locationWithText: String
        get() = TODO("Not yet implemented")
    override val moduleName: String
        get() = TODO("Not yet implemented")
    override val name: String
        get() = TODO("Not yet implemented")
    override val path: String
        get() = TODO("Not yet implemented")
    override val projectPath: String
        get() = TODO("Not yet implemented")
    override val sourceDeclaration: KoDeclarationCastProvider
        get() = TODO("Not yet implemented")
    override val sourceSetName: String
        get() = TODO("Not yet implemented")
    override val text: String
        get() = importString

    override fun hasAlias(predicate: ((KoImportAliasDeclaration) -> Boolean)?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasNameContaining(text: String): Boolean = importString.contains(text)

    override fun hasNameEndingWith(suffix: String): Boolean = importString.endsWith(suffix)

    override fun hasNameMatching(regex: Regex): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasNameStartingWith(prefix: String): Boolean = importString.startsWith(prefix)

    override fun hasSourceDeclaration(predicate: (KoDeclarationCastProvider) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean {
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

    override fun toString(): String = importString

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KonsistKspKoImportDeclaration

        return importString == other.importString
    }

    override fun hashCode(): Int = importString.hashCode()
}
