package fr.tokazio.archivald.konsist.bridge.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import kotlin.reflect.KClass

interface KonsistKspKoAnnotationProvider : KoAnnotationProvider {
    override fun countAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Int = annotations.size

    override fun hasAllAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean = annotations.all(predicate)

    override fun hasAllAnnotationsOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else -> names.all { checkIfAnnotated(it) }
        }

    override fun hasAllAnnotationsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasAllAnnotationsOf(listOf(name, *names))

    override fun hasAnnotation(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean = annotations.any(predicate)

    override fun hasAnnotationWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasAnnotationWithName(listOf(name, *names))

    override fun hasAnnotationWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else ->
                names.any {
                    annotations.any { annotation -> annotation.representsType(it) }
                }
        }

    override fun hasAnnotations(): Boolean = annotations.isNotEmpty()

    override fun hasAnnotationsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasAnnotationsWithAllNames(listOf(name, *names))

    override fun hasAnnotationsWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else ->
                names.all {
                    annotations.any { annotation -> annotation.representsType(it) }
                }
        }

    override fun hasAnnotationOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> hasAnnotations()
            else -> names.any { checkIfAnnotated(it) }
        }

    override fun hasAnnotationOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasAnnotationOf(listOf(name, *names))

    private fun checkIfAnnotated(kClass: KClass<*>): Boolean =
        annotations.any { annotation ->
            if (kClass.qualifiedName?.startsWith("kotlin.") == true) {
                annotation.name == kClass.simpleName
            } else {
                annotation.fullyQualifiedName == kClass.qualifiedName
            }
        }
}
