package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.symbol.*
import fr.tokazio.konsistksp.internal.model.*
import fr.tokazio.konsistksp.internal.model.Annotation
import fr.tokazio.konsistksp.internal.model.Modifier
import org.jetbrains.kotlin.utils.mapToSetOrEmpty

fun KSDeclaration.notInClassPath(): String =
    throw IllegalStateException(
        "Can't get FQName of ${this::class.java.simpleName} ${simpleName.asString()} because it's not in the classpath",
    )

fun KSAnnotation.notInClassPath(): String =
    throw IllegalStateException("Can't get FQName of annotation ${shortName.asString()} because it's not in the classpath")

fun KSModifierListOwner.extractModifiers(): Set<Modifier> =
    modifiers
        .mapToSetOrEmpty {
            Modifier.valueOf(it.name)
        }

fun KSDeclarationContainer.extractDeclarations(): Sequence<Declaration> =
    this.let { declarationContainer ->
        declarations
            .map {
                when (it) {
                    is KSClassDeclaration -> KonsistKspClassDeclaration(it, it.containingFile!!)
                    is KSFunctionDeclaration -> KonsistKspFunctionDeclaration(it)
                    is KSPropertyDeclaration -> KonsistKspPropertyDeclaration(it)
                    else -> throw IllegalStateException(
                        "Unhandled ${declarationContainer::class.java.simpleName}.declarations ${it::class.java.simpleName} $it",
                    )
                }
            }
    }

fun KSNode.extractParent(): Declaration =
    this.let { me ->
        when (me.parent) {
            is KSClassDeclaration ->
                KonsistKspClassDeclaration(
                    me.parent as KSClassDeclaration,
                    containingFile!!,
                )

            is KSFunctionDeclaration ->
                KonsistKspFunctionDeclaration(me.parent as KSFunctionDeclaration)

            is KSFile ->
                KonsistKspFile(me.parent as KSFile)

            else -> throw IllegalStateException(
                "Unhandled ${me::class.java.simpleName}.parent ${
                    if (me.parent != null) {
                        me.parent!!::class
                            .java.simpleName
                    } else {
                        "null"
                    }
                } ",
            )
        }
    }

fun KSDeclarationContainer.extractContainingDeclaration(): Declaration = this.extractParent()

fun KSAnnotated.extractAnnotations(): Sequence<Annotation> =
    annotations.map {
        KonsistKspAnnotation(it)
    }

fun Node?.asClazz(): ClassDeclaration = KonsistKspClassDeclaration((this as KonsistKspNode).inner as KSClassDeclaration, findFile(inner))

fun Annotated.asFunction(): FunctionDeclaration =
    KonsistKspFunctionDeclaration(
        (this as KonsistKspAnnotated).inner as KSFunctionDeclaration,
    )

private fun findFile(node: KSNode?): KSFile =
    node?.let {
        it as? KSFile ?: findFile(it.parent)
    } ?: throw IllegalStateException("No root File")
