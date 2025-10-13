package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.container.KoScope
import fr.tokazio.konsistksp.KonsistKspScopeCreator
import fr.tokazio.konsistksp.internal.SymbolResolver
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.File

class KonsistKspKoScopeCreator(
    private val logger: Logger,
    private val resolver: SymbolResolver,
) : KonsistKspScopeCreator {
    override fun scopeFromPackage(
        packagee: String,
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope =
        KonsistKspKoScope(
            logger,
            resolver
                .getAllFiles()
                .filter { file: File ->
                    if (packagee.endsWith("..")) {
                        file.packageName
                            .startsWith(packagee.removeSuffix(".."))
                    } else {
                        file.packageName == packagee
                    }
                }.map { file: File ->
                    KonsistKspKoFileDeclaration(logger, file)
                }.toList(),
        )

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override val projectRootPath: String
        get() = TODO("Not yet implemented")

    override fun scopeFromDirectories(paths: Collection<String>): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromDirectory(
        path: String,
        vararg paths: String,
    ): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromExternalDirectories(absolutePaths: Collection<String>): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromExternalDirectory(
        absolutePath: String,
        vararg paths: String,
    ): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromFile(
        path: String,
        vararg paths: String,
    ): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromFiles(paths: Collection<String>): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromModule(
        moduleName: String,
        vararg moduleNames: String,
    ): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromModules(moduleNames: Collection<String>): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromProduction(
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromProject(
        moduleName: String?,
        sourceSetName: String?,
        ignoreBuildConfig: Boolean,
    ): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromSourceSet(
        sourceSetName: String,
        vararg sourceSetNames: String,
    ): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromSourceSets(sourceSetNames: Collection<String>): KoScope {
        TODO("Not yet implemented")
    }

    override fun scopeFromTest(
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope {
        TODO("Not yet implemented")
    }
}
