package fr.tokazio.archivald.jap.bridge

import fr.tokazio.archivald.internal.SymbolResolver
import fr.tokazio.archivald.internal.model.Annotated
import fr.tokazio.archivald.internal.model.File
import javax.annotation.processing.RoundEnvironment
import kotlin.reflect.KClass

class ArchivaldJapSymbolResolver(
    private val roundEnv: RoundEnvironment,
) : SymbolResolver {
    override fun getSymbolsWithAnnotation(annotationKlass: KClass<out Annotation>): Sequence<Annotated> =
        roundEnv.getElementsAnnotatedWith(annotationKlass.java).asSequence().map {
            JapAnnotated(it)
        }

    override fun getAllFiles(): Sequence<File> {
        TODO("Not yet implemented")
    }
}
