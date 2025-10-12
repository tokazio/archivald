package fr.tokazio.konsistksp.internal.model

interface FunctionDeclaration : Declaration {
    val parameters: List<ValueParameter>
}
