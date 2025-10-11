package fr.tokazio.konsistksp.ksp

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

interface CommonSymbolProcessorProvider: SymbolProcessorProvider {
    val name : String

    fun displayOptions(environment: SymbolProcessorEnvironment){
        if(environment.options.isEmpty()) {
            environment.logger.info("$name option: no option found, define at least the 'konsistKspProjectBase'")
        }else {
            environment.options.forEach {
                environment.logger.info("$name option: ${it.key}=${it.value}")
            }
        }
    }
}