package fr.tokazio.konsistksp.api

import com.google.devtools.ksp.symbol.KSValueParameter

interface Function {
    val parameters: List<ValueParameter>
}