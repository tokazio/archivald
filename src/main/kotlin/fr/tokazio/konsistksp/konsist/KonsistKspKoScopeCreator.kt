package fr.tokazio.konsistksp.konsist

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSFile
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.container.KoScopeCreator
import fr.tokazio.konsistksp.logger.KonsistKspLogger

class KonsistKspKoScopeCreator(
  private val logger: KonsistKspLogger,
  private val resolver: Resolver,
) : KoScopeCreator {
  override val projectRootPath: String
    get() = TODO("Not yet implemented")

  override fun scopeFromDirectories(paths: Collection<String>): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromDirectory(path: String, vararg paths: String): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromExternalDirectories(absolutePaths: Collection<String>): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromExternalDirectory(absolutePath: String, vararg paths: String): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromFile(path: String, vararg paths: String): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromFiles(paths: Collection<String>): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromModule(moduleName: String, vararg moduleNames: String): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromModules(moduleNames: Collection<String>): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromPackage(packagee: String, moduleName: String?, sourceSetName: String?): KoScope =
    KonsistKspKoScope(
      logger,
      resolver.getAllFiles()
        .filter { ksFile: KSFile ->
          if (packagee.endsWith("..")) {
            ksFile.packageName.asString()
              .startsWith(packagee.removeSuffix("."))
          } else {
            ksFile.packageName.asString() == packagee
          }
        }
        .map { ksFile: KSFile ->
          KonsistKspKoFileDeclaration(logger, ksFile)
        }
        .toList()
    )

  override fun scopeFromProduction(moduleName: String?, sourceSetName: String?): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromProject(moduleName: String?, sourceSetName: String?, ignoreBuildConfig: Boolean): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromSourceSet(sourceSetName: String, vararg sourceSetNames: String): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromSourceSets(sourceSetNames: Collection<String>): KoScope {
    TODO("Not yet implemented")
  }

  override fun scopeFromTest(moduleName: String?, sourceSetName: String?): KoScope {
    TODO("Not yet implemented")
  }
}