package fr.tokazio.konsistksp.api

interface Declaration : Annotated {
    val declarations: Sequence<Declaration>
}
