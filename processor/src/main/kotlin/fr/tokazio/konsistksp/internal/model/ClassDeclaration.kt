package fr.tokazio.konsistksp.internal.model

import java.io.File.separator

interface ClassDeclaration : Declaration {
    val qualifiedName: String
    val packageName: String

    val isCompanionObject: Boolean

    val isObject: Boolean
    val isInterface: Boolean
    val isClass: Boolean
    val isEnum: Boolean
    val isAnnotation: Boolean

    val modifiers: Set<Modifier>

    val sourceSetName: String
        get() =
            containingFile
                .filePath
                .substringAfter("${separator}src$separator")
                .substringBefore(separator)

    val containingFile: File
    // val containingDeclaration: BaseDeclaration
}
