package fr.tokazio.archivald.internal

import kotlin.reflect.KClass

interface SymbolResolver {
    fun getSymbolsWithAnnotation(annotationKlass: KClass<out Annotation>): Sequence<fr.tokazio.archivald.internal.model.Annotated>

    fun getAllFiles(): Sequence<fr.tokazio.archivald.internal.model.File>
}
