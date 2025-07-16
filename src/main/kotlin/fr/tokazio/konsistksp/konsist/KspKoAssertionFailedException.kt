package fr.tokazio.konsistksp.konsist

class KspKoAssertionFailedException(
  val testName: String,
  val additionalMessage: String?,
  val failedItems: List<Any>,
) : Exception()
