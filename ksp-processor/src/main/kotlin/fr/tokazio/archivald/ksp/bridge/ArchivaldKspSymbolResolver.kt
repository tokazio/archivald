package fr.tokazio.archivald.ksp.bridge

import com.google.devtools.ksp.processing.Resolver
import fr.tokazio.archivald.internal.SymbolResolver
import fr.tokazio.archivald.ksp.bridge.model.ArchivaldKspAnnotated
import fr.tokazio.archivald.ksp.bridge.model.KonsistKspFile
import kotlin.reflect.KClass

class ArchivaldKspSymbolResolver(
    private val resolver: Resolver,
) : SymbolResolver {
    override fun getSymbolsWithAnnotation(annotationKlass: KClass<out Annotation>) =
        resolver.getSymbolsWithAnnotation(annotationKlass.java.name).map {
            ArchivaldKspAnnotated(it)
        }

    override fun getAllFiles() =
        resolver.getAllFiles().map {
            KonsistKspFile(it)
        }
}
