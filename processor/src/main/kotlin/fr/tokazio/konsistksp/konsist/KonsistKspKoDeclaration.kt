package fr.tokazio.konsistksp.konsist

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import fr.tokazio.konsistksp.api.Declaration
import fr.tokazio.konsistksp.api.Logger

class KonsistKspKoDeclaration(
    private val logger: Logger,
    private val inner: Declaration,
) : KoBaseDeclaration {
    override fun toString(): String = inner.toString()
}
