package fr.tokazio.konsistksp.internal.model

interface PropertyDeclaration : Declaration {
    val name: String
    val modifiers: List<Modifier>
}
