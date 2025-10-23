package fr.tokazio.archivald.konsist.bridge.provider

import com.lemonappdev.konsist.api.provider.KoTextProvider

interface KonsistKspKoTextProvider : KoTextProvider {
    override fun hasTextContaining(str: String): Boolean = text.contains(str)

    override fun hasTextEndingWith(suffix: String): Boolean = text.endsWith(suffix)

    override fun hasTextMatching(regex: Regex): Boolean = text.contains(regex)

    override fun hasTextStartingWith(prefix: String): Boolean = text.startsWith(prefix)
}
