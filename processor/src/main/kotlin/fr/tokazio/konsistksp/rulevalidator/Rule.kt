package fr.tokazio.konsistksp.rulevalidator

import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.KonsistKspScopeCreator

data class Rule(
    val targetInstance: Any,
    val methodName: String,
) {
    val className = targetInstance::class.java.simpleName

    val name: String = "$className.$methodName"

    val method by lazy {
        try {
            targetInstance.javaClass.getMethod(
                methodName,
                KonsistKspScopeCreator::class.java,
                String::class.java, // 'base package' parameter
            )
        } catch (_: NoSuchMethodException) {
            // Fallback to a version without the 'base package' parameter
            targetInstance.javaClass.getMethod(methodName, KonsistKspScopeCreator::class.java)
        }
    }

    // TODO handle multiple annotation instead of one
    val description by lazy {
        (method.annotations.first() as ArchitectureRule).value.ifEmpty { methodName }
    }
}
