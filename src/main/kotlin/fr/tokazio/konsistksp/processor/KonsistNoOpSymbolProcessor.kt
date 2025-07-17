package fr.tokazio.konsistksp.processor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated

class KonsistNoOpSymbolProcessor : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> = emptyList()
}