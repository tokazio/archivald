package fr.tokazio.archivald.internal.model

interface FunctionDeclaration : Declaration {
    val modifiers: Set<Modifier>
    val parameters: List<ValueParameter>
    val returnType: TypeReference?
    val containingDeclaration: Declaration
}
