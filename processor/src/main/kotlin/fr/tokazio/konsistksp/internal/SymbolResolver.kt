package fr.tokazio.konsistksp.internal

import fr.tokazio.konsistksp.internal.model.Annotated
import fr.tokazio.konsistksp.internal.model.File
import kotlin.reflect.KClass

interface SymbolResolver {
    fun getSymbolsWithAnnotation(annotationKlass: KClass<*>): Sequence<Annotated>

    fun getAllFiles(): Sequence<File>
}
