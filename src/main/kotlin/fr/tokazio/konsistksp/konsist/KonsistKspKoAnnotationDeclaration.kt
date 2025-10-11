package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import fr.tokazio.konsistksp.api.Logger

class KonsistKspKoAnnotationDeclaration(
  private val logger: Logger,
  private val annotation: fr.tokazio.konsistksp.api.Annotation,
) : KoAnnotationDeclaration {
  override val arguments: List<KoArgumentDeclaration>
    get() = TODO("Not yet implemented")
  override val containingFile: KoFileDeclaration
    get() = TODO("Not yet implemented")
  override val fullyQualifiedName: String?
    get() = TODO("Not yet implemented")
  override val location: String
    get() = TODO("Not yet implemented")
  override val locationWithText: String
    get() = TODO("Not yet implemented")
  override val moduleName: String
    get() = TODO("Not yet implemented")
  override val name: String
    get() = TODO("Not yet implemented")
  override val numArguments: Int
    get() = TODO("Not yet implemented")
  override val path: String
    get() = TODO("Not yet implemented")
  override val projectPath: String
    get() = TODO("Not yet implemented")
  override val sourceSetName: String
    get() = TODO("Not yet implemented")
  override val text: String
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

  override fun hasArgumentWithName(name: String, vararg names: String): Boolean {
    TODO("Not yet implemented")
  }

  override fun hasArgumentWithName(names: Collection<String>): Boolean {
    TODO("Not yet implemented")
  }

  override fun hasArguments(): Boolean {
    TODO("Not yet implemented")
  }

  override fun hasArgumentsWithAllNames(name: String, vararg names: String): Boolean {
    TODO("Not yet implemented")
  }

  override fun hasArgumentsWithAllNames(names: Collection<String>): Boolean {
    TODO("Not yet implemented")
  }

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

  override fun representsType(name: String?): Boolean {
    TODO("Not yet implemented")
  }

  override fun resideInModule(name: String): Boolean {
    TODO("Not yet implemented")
  }

  override fun resideInPath(path: String, absolutePath: Boolean): Boolean {
    TODO("Not yet implemented")
  }

  override fun resideInSourceSet(sourceSetName: String): Boolean {
    TODO("Not yet implemented")
  }

  override fun toString(): String {
    TODO("Not yet implemented")
  }
}