package fr.tokazio.konsistksp.konsist

import com.google.devtools.ksp.symbol.*

class KSImport(
  override val location: FileLocation,
  override val origin: Origin,
  override val parent: KSFile,
) : KSNode {
  override fun <D, R> accept(
    visitor: KSVisitor<D, R>,
    data: D,
  ): R {
    TODO("Not yet implemented")
  }
}
