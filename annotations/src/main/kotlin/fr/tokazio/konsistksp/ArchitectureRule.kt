package fr.tokazio.konsistksp

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ArchitectureRule(
    val value: String = "", // Failure message
    val uuid: String = "",
    val level: RuleLevel = RuleLevel.ERROR,
)
