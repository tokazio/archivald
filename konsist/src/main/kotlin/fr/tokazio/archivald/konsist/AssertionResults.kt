package fr.tokazio.archivald.konsist

object AssertionResults {
    val results = mutableListOf<KonsistKspKoAssertionFailedException>()

    fun clear() {
        results.clear()
    }

    fun add(ex: KonsistKspKoAssertionFailedException) {
        results.add(ex)
    }

    fun asOneException(): KonsistKspKoAssertionFailedException =
        KonsistKspKoAssertionFailedException(
            testName = results.first().testName,
            additionalMessage = results.first().testName,
            failedItems =
                results.flatMap {
                    it.failedItems
                },
        )
}
