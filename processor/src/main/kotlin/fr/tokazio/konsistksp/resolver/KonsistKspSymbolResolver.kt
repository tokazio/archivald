package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.processing.Resolver
import fr.tokazio.konsistksp.api.SymbolResolver
import kotlin.reflect.KClass

class KonsistKspSymbolResolver(
    private val resolver: Resolver,
): SymbolResolver {
    override fun getSymbolsWithAnnotation(annotationKlass: KClass<*>) = resolver.getSymbolsWithAnnotation(annotationKlass.java.name).map{
        KonsistKspAnnotated(it)
    }

    override fun getAllFiles() = resolver.getAllFiles().map{
        KonsistKspFile(it)
    }
}