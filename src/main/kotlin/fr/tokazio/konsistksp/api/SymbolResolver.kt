package fr.tokazio.konsistksp.api

import kotlin.reflect.KClass

interface SymbolResolver{
    fun getSymbolsWithAnnotation(annotationKlass: KClass<*>): Sequence<Annotated>
    fun getAllFiles(): Sequence<File>
}