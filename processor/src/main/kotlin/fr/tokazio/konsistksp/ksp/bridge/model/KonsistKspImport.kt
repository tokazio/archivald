package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.*

class KonsistKspImport(
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
