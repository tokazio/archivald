package fr.tokazio.archivald.rulevalidator

/**
 * Find .class from somewhere
 */
interface RuleFinder {
    fun find(): Set<String>
}
