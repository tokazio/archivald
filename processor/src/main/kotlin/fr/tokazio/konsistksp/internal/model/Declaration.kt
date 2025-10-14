package fr.tokazio.konsistksp.internal.model

interface Declaration : Annotated {
    val simpleName: String
    val qualifiedName: String
    val packageName: String
    val containingFile: File
    val declarations: Sequence<Declaration>
}
