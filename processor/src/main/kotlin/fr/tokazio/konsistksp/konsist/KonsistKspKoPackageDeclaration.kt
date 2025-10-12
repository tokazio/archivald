package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.util.PathUtil.separator
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.ClassDeclaration

class KonsistKspKoPackageDeclaration(
    private val logger: Logger,
    private val inner: ClassDeclaration,
) : KoPackageDeclaration {
    override val containingFile: KoFileDeclaration = KonsistKspKoFileDeclaration(logger, inner.containingFile)

    override val location: String
        get() = TODO("Not yet implemented")
    override val locationWithText: String
        get() = TODO("Not yet implemented")

    override fun hasNameContaining(text: String): Boolean = inner.packageName.contains(text)

    override fun hasNameEndingWith(suffix: String): Boolean = inner.packageName.endsWith(suffix)

    override fun hasNameMatching(regex: Regex): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasNameStartingWith(prefix: String): Boolean = inner.packageName.startsWith(prefix)

    override val name: String = inner.packageName

    override val hasMatchingPath: Boolean =
        path
            .replace(separator, ".")
            .endsWith(name + "." + containingFile.nameWithExtension)

    override fun resideInPath(
        path: String,
        absolutePath: Boolean,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override val path: String
        get() = inner.containingFile.filePath

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

    override fun hasTextContaining(str: String): Boolean = text.contains(str)

    override fun hasTextEndingWith(suffix: String): Boolean = text.endsWith(suffix)

    override fun hasTextMatching(regex: Regex): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasTextStartingWith(prefix: String): Boolean = text.startsWith(prefix)

    override val text: String
        get() = TODO("Not yet implemented")

    override fun toString(): String = inner.toString()
}
