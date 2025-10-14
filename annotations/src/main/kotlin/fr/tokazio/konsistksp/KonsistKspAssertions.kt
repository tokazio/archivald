package fr.tokazio.konsistksp

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.verify.checkIfLocalListHasOnlyNullElements
import com.lemonappdev.konsist.core.verify.checkIfLocalListIsEmpty

fun <E : KoBaseProvider> List<E?>.assertTrue(
    strict: Boolean = false,
    additionalMessage: String? = null,
    testName: String? = null,
    function: (E) -> Boolean?,
) {
    assertKsp(strict, additionalMessage, testName, function, positiveCheck = true)
}

fun <E : KoBaseProvider> E?.assertTrue(
    strict: Boolean = false,
    additionalMessage: String? = null,
    testName: String? = null,
    function: (E) -> Boolean?,
) {
    listOf(this).assertKsp(strict, additionalMessage, testName, function, positiveCheck = true)
}

fun <E : KoBaseProvider> List<E?>.assertFalse(
    strict: Boolean = false,
    additionalMessage: String? = null,
    testName: String? = null,
    function: (E) -> Boolean?,
) {
    assertKsp(strict, additionalMessage, testName, function, positiveCheck = false)
}

fun <E : KoBaseProvider> E?.assertFalse(
    strict: Boolean = false,
    additionalMessage: String? = null,
    testName: String? = null,
    function: (E) -> Boolean?,
) {
    listOf(this).assertKsp(strict, additionalMessage, testName, function, positiveCheck = false)
}

private fun <E : KoBaseProvider> List<E?>.assertKsp(
    strict: Boolean,
    additionalMessage: String?,
    testName: String?,
    function: (E) -> Boolean?,
    positiveCheck: Boolean,
) {
    val fifthIndexMethodName = getTestMethodNameFromFifthIndex()

    val testMethodName =
        testName
            ?: if (fifthIndexMethodName.contains("\$default")) {
                getTestMethodNameFromSixthIndex()
            } else {
                fifthIndexMethodName
            }

    val assertMethodName = getTestMethodNameFromFourthIndex()

    if (strict) {
        checkIfLocalListIsEmpty(this, assertMethodName)
        checkIfLocalListHasOnlyNullElements(this, assertMethodName)
    }

    val localSuppressName = testName ?: testMethodName

    val notSuppressedDeclarations = checkIfAnnotatedWithSuppress(this.filterNotNull(), localSuppressName)

    val result =
        notSuppressedDeclarations.groupBy {
            function(it) ?: positiveCheck
        }

    getResult(
        items = notSuppressedDeclarations,
        result = result,
        positiveCheck = positiveCheck,
        testName = localSuppressName,
        additionalMessage = additionalMessage,
    )
}

private fun getTestMethodNameFromFourthIndex() = getTestMethodName(4)

private fun getTestMethodNameFromFifthIndex() = getTestMethodName(5)

private fun getTestMethodNameFromSixthIndex() = getTestMethodName(6)

private fun getTestMethodName(index: Int): String =
    Thread
        .currentThread()
        .stackTrace[index]
        .methodName

private fun <E : KoBaseProvider> checkIfAnnotatedWithSuppress(
    localList: List<E>,
    suppressName: String,
): List<E> {
    val declarations: MutableMap<E, Boolean> = mutableMapOf()

    // First we need to exclude (if exist) file suppress test annotation
    localList
        .filterNot {
            it is KoAnnotationDeclaration &&
                (
                    it.name == "Suppress" &&
                        it.hasTextContaining("\"konsist.$suppressName\"") ||
                        it.hasTextContaining("\"$suppressName\"")
                )
        }.forEach { declarations[it] = checkIfDeclarationIsAnnotatedWithSuppress(it as KoBaseDeclaration, suppressName) }

    val withoutSuppress = mutableListOf<E>()

    declarations.forEach { if (!it.value) withoutSuppress.add(it.key) }

    return withoutSuppress
}

private fun checkIfDeclarationIsAnnotatedWithSuppress(
    declaration: KoBaseDeclaration,
    testMethodName: String,
): Boolean =
    when (declaration) {
        is KoFileDeclaration -> {
            checkIfSuppressed(declaration, testMethodName)
        }

        is KoAnnotationProvider -> {
            checkIfSuppressed(declaration, testMethodName) ||
                checkIfParentIsAnnotatedWithSuppress(declaration, testMethodName)
        }

        else -> {
            checkIfParentIsAnnotatedWithSuppress(declaration, testMethodName)
        }
    }

private fun checkIfSuppressed(
    item: KoAnnotationProvider,
    testMethodName: String,
): Boolean {
    val annotationParameter =
        item
            .annotations
            .firstOrNull { it.name == "Suppress" }
            ?.text
            ?.removePrefix("@file:Suppress(")
            ?.removePrefix("@Suppress(")
            ?.substringBeforeLast(")")
            ?.split(",")
            ?.map { it.trim() }
            ?.map { it.removePrefix("\"") }
            ?.map { it.removeSuffix("\"") }
            .orEmpty()

    return annotationParameter.any { it == testMethodName } || annotationParameter.any { it == "konsist.$testMethodName" }
}

private fun checkIfParentIsAnnotatedWithSuppress(
    declaration: KoBaseDeclaration,
    testMethodName: String,
): Boolean =
    if (declaration is KoContainingDeclarationProvider) {
        checkIfDeclarationIsAnnotatedWithSuppress(declaration.containingDeclaration, testMethodName)
    } else {
        false
    }

private fun getResult(
    items: List<*>,
    result: Map<Boolean, List<Any>>,
    positiveCheck: Boolean,
    testName: String,
    additionalMessage: String?,
) {
    val allChecksPassed = (result[positiveCheck]?.size ?: 0) == items.size
    if (!allChecksPassed) {
        val failedItems = result[!positiveCheck].orEmpty()
        AssertionResults.add(
            KonsistKspKoAssertionFailedException(
                testName,
                additionalMessage,
                failedItems,
            ),
        )
    }
}
