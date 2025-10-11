package fr.tokazio.konsistksp

class KonsistKspKoAssertionFailedException(
    val testName: String,
    val additionalMessage: String?,
    val failedItems: List<Any>,
) : Exception()
