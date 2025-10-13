package fr.tokazio.konsistksp.internal.model

interface FunctionDeclaration : Declaration {
    val name: String
    val modifiers: List<Modifier>
    val parameters: List<ValueParameter>
    val returnType: TypeReference?
}
