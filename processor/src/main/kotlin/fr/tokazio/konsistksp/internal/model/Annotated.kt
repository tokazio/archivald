package fr.tokazio.konsistksp.internal.model

interface Annotated : Node {
    val annotations: Sequence<Annotation>
}
