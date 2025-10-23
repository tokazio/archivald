package fr.tokazio.archivald.internal.model

interface ValueParameter : Annotated {
    val name: String
    val typeReference: TypeReference
    val containingDeclaration: Declaration
}
