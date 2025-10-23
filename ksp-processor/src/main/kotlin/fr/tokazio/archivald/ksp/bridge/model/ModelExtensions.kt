package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.symbol.*
import fr.tokazio.archivald.internal.model.Declaration

fun KSDeclaration.notInClassPath(): String =
    throw IllegalStateException(
        "Can't get FQName of ${this::class.java.simpleName} ${simpleName.asString()} because it's not in the classpath",
    )

fun KSAnnotation.notInClassPath(): String =
    throw IllegalStateException("Can't get FQName of annotation ${shortName.asString()} because it's not in the classpath")

fun KSModifierListOwner.extractModifiers(): Set<fr.tokazio.archivald.internal.model.Modifier> =
    modifiers
        .mapTo(mutableSetOf()) {
            fr.tokazio.archivald.internal.model.Modifier
                .valueOf(it.name)
        }

fun KSDeclarationContainer.extractDeclarations(): Sequence<Declaration> =
    this.let { declarationContainer ->
        declarations
            .map {
                when (it) {
                    is KSClassDeclaration -> KonsistKspClassDeclaration(it, it.containingFile!!)
                    is KSFunctionDeclaration -> KonsistKspFunctionDeclaration(it)
                    is KSPropertyDeclaration -> KonsistKspPropertyDeclaration(it)
                    is KSTypeAlias -> KonsistKspTypeAliasDeclaration(it)
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

fun KSAnnotated.extractAnnotations(): Sequence<fr.tokazio.archivald.internal.model.Annotation> =
    annotations.map {
        KonsistKspAnnotation(it)
    }
