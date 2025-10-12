package fr.tokazio.konsistksp.internal.model

interface Declaration : Annotated {
    val declarations: Sequence<Declaration>
}
