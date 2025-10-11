package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSVisitor
import com.google.devtools.ksp.symbol.Origin
import fr.tokazio.konsistksp.api.Symbol

class KonsistKspImport(
    override val location: FileLocation,
    override val origin: Origin,
    override val parent: KSFile,
) : KSNode, Symbol {
  override fun <D, R> accept(
      visitor: KSVisitor<D, R>,
      data: D,
  ): R {
    TODO("Not yet implemented")
  }
}