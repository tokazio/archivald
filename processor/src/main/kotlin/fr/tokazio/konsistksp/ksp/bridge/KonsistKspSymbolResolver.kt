package fr.tokazio.konsistksp.ksp.bridge

import com.google.devtools.ksp.processing.Resolver
import fr.tokazio.konsistksp.internal.SymbolResolver
import fr.tokazio.konsistksp.ksp.bridge.model.KonsistKspAnnotated
import fr.tokazio.konsistksp.ksp.bridge.model.KonsistKspFile
import kotlin.reflect.KClass

class KonsistKspSymbolResolver(
    private val resolver: Resolver,
) : SymbolResolver {
    override fun getSymbolsWithAnnotation(annotationKlass: KClass<*>) =
        resolver.getSymbolsWithAnnotation(annotationKlass.java.name).map {
            KonsistKspAnnotated(it)
        }

    override fun getAllFiles() =
        resolver.getAllFiles().map {
            KonsistKspFile(it)
        }
}
