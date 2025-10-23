package fr.tokazio.archivald.internal

interface RuleProcessor {
    fun process(resolver: fr.tokazio.archivald.internal.SymbolResolver): List<fr.tokazio.archivald.internal.model.Annotated>
}
