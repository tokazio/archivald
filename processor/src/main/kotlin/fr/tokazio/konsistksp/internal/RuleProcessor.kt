package fr.tokazio.konsistksp.internal

import fr.tokazio.konsistksp.internal.model.Annotated

interface RuleProcessor {
    fun process(resolver: SymbolResolver): List<Annotated>
}
