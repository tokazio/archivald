package fr.tokazio.archivald.ksp.rulevalidator

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated

class ArchivaldRuleNoOpValidatorSymbolProcessor : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> = emptyList()
}
