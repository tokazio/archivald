package fr.tokazio.archivald.internal.model

interface Annotated : Node {
    val annotations: Sequence<Annotation>

    fun asFunction(): FunctionDeclaration
}
