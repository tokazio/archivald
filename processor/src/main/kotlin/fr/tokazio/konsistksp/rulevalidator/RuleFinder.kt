package fr.tokazio.konsistksp.rulevalidator

/**
 * Find .class from somewhere
 */
interface RuleFinder {
    fun find(): Set<String>
}
