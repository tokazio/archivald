package fr.tokazio.archivald.konsist.bridge.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReturnProvider
import kotlin.reflect.KClass

interface KonsistKspKoReturnProvider : KoReturnProvider {
    override fun hasReturnType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean = hasReturnValue

    override fun hasReturnTypeOf(kClass: KClass<*>): Boolean {
        TODO("Not yet implemented")
    }
}
