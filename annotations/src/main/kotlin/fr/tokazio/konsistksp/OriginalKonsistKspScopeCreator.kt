package fr.tokazio.konsistksp

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.container.KoScope

class OriginalKonsistKspScopeCreator : KonsistKspScopeCreator {
    override fun scopeFromDirectories(paths: Collection<String>): KoScope = Konsist.scopeFromDirectories(paths)

    override fun scopeFromDirectory(
        path: String,
        vararg paths: String,
    ): KoScope = Konsist.scopeFromDirectory(path, *paths)

    override fun scopeFromExternalDirectories(absolutePaths: Collection<String>): KoScope =
        Konsist.scopeFromExternalDirectories(absolutePaths)

    override fun scopeFromExternalDirectory(
        absolutePath: String,
        vararg paths: String,
    ): KoScope = Konsist.scopeFromExternalDirectory(absolutePath, *paths)

    override fun scopeFromFile(
        path: String,
        vararg paths: String,
    ): KoScope = Konsist.scopeFromFile(path, *paths)

    override fun scopeFromFiles(paths: Collection<String>): KoScope = Konsist.scopeFromFiles(paths)

    override fun scopeFromModule(
        moduleName: String,
        vararg moduleNames: String,
    ): KoScope = Konsist.scopeFromModule(moduleName, *moduleNames)

    override fun scopeFromModules(moduleNames: Collection<String>): KoScope = Konsist.scopeFromModules(moduleNames)

    override fun scopeFromPackage(
        packagee: String,
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope = Konsist.scopeFromPackage(packagee, moduleName, sourceSetName)

    override fun scopeFromProduction(
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope = Konsist.scopeFromProduction(moduleName, sourceSetName)

    override fun scopeFromProject(
        moduleName: String?,
        sourceSetName: String?,
        ignoreBuildConfig: Boolean,
    ): KoScope = Konsist.scopeFromProject(moduleName, sourceSetName, ignoreBuildConfig)

    override fun scopeFromSourceSet(
        sourceSetName: String,
        vararg sourceSetNames: String,
    ): KoScope = Konsist.scopeFromSourceSet(sourceSetName, sourceSetName)

    override fun scopeFromSourceSets(sourceSetNames: Collection<String>): KoScope = Konsist.scopeFromSourceSets(sourceSetNames)

    override fun scopeFromTest(
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope = Konsist.scopeFromTest(moduleName, sourceSetName)

    override val projectRootPath: String
        get() = Konsist.projectRootPath
}
