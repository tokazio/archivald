package fr.tokazio.archivald.konsist

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.architecture.validator.ascii.AsciiTreeCreator
import com.lemonappdev.konsist.core.architecture.validator.ascii.AsciiTreeNode
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.util.HyperlinkUtil

class KonsistKspKoAssertionFailedException(
    val testName: String,
    val additionalMessage: String?,
    val failedItems: List<Any>,
) : Exception() {
    fun asKoAssertionFailedException(): Unit =
        throw KoAssertionFailedException(
            getCheckFailedMessage(failedItems, testName, additionalMessage),
        )

    private fun getCheckFailedMessage(
        failedItems: List<*>,
        testName: String,
        additionalMessage: String?,
    ): String {
        val (types, failedDeclarationsMessage) = processFailedItems(failedItems)

        val customMessage = additionalMessage?.let { "\n$it\n" } ?: " "
        val times = if (failedItems.size == 1) "time" else "times"

        val getRootMessage =
            "Assert '$testName' was violated (${failedItems.size} $times).$customMessage" +
                "Invalid $types:"

        val failedDeclarationAsciiTreeNodes = failedDeclarationsMessage.map { AsciiTreeNode(it, emptyList()) }

        return AsciiTreeCreator().invoke(
            AsciiTreeNode(
                getRootMessage,
                failedDeclarationAsciiTreeNodes,
            ),
        )
    }

    private fun processFailedItems(failedItems: List<*>): Pair<String, List<String>> {
        var types = ""
        val failedDeclarationsMessage =
            failedItems.map { item ->
                when (item) {
                    is KoFileDeclaration -> {
                        types = "files"

                        val hyperlinkUrl = HyperlinkUtil.toHyperlink(item.path)

                        "${
                            getFailedNameWithDeclarationType(
                                item.nameWithExtension,
                                item.getDeclarationType(),
                            )
                        } $hyperlinkUrl"
                    }

                    is KoBaseProvider -> {
                        types = "declarations"
                        val name = (item as? KoNameProvider)?.name
                        val location = (item as? KoLocationProvider)?.location

                        val hyperlinkUrl = location?.let { HyperlinkUtil.toHyperlink(it) }

                        "${getFailedNameWithDeclarationType(name, item.getDeclarationType())} $hyperlinkUrl"
                    }

                    else -> ""
                }
            }

        return Pair(types, failedDeclarationsMessage)
    }

    private fun getFailedNameWithDeclarationType(
        name: String?,
        declarationType: String?,
    ) = if (name != null) "$declarationType $name" else "$declarationType"
}

private fun Any.getDeclarationType(): String? =
    this::class
        .simpleName
        ?.removePrefix("Ko")
        ?.removeSuffix("DeclarationCore")
