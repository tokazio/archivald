package fr.tokazio.konsistksp.konsist.provider

import com.lemonappdev.konsist.api.provider.KoNameProvider

interface KonsistKspKoNameProvider : KoNameProvider {
    override fun hasNameContaining(text: String): Boolean = name.contains(text)

    override fun hasNameEndingWith(suffix: String): Boolean = name.endsWith(suffix)

    override fun hasNameMatching(regex: Regex): Boolean = name.contains(regex)

    override fun hasNameStartingWith(prefix: String): Boolean = name.startsWith(prefix)
}
