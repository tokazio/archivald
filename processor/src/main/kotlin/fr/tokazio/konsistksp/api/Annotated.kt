package fr.tokazio.konsistksp.api

interface Annotated : Node {
    val annotations: Sequence<Annotation>
}
