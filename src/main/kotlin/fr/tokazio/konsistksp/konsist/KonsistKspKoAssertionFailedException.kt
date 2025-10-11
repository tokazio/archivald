package fr.tokazio.konsistksp.konsist

class KonsistKspKoAssertionFailedException(
  val testName: String,
  val additionalMessage: String?,
  val failedItems: List<Any>,
) : Exception()
