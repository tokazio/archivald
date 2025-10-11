package fr.tokazio.konsistksp.api

interface RuleProcessor {
    fun process(resolver: SymbolResolver): List<Annotated>
}