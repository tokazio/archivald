package fr.tokazio.archivald.konsist.bridge.provider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider

interface KonsistKspKoModifierProvider : KoModifierProvider {
    override fun hasAllModifiers(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean = hasAllModifiers(listOf(modifier, *modifiers))

    override fun hasAllModifiers(modifiers: Collection<KoModifier>): Boolean =
        when {
            modifiers.isEmpty() -> hasModifiers()
            else -> this.modifiers.containsAll(modifiers)
        }

    override fun hasModifier(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean = hasModifier(listOf(modifier, *modifiers))

    override fun hasModifier(modifiers: Collection<KoModifier>): Boolean =
        when {
            modifiers.isEmpty() -> hasModifiers()
            else -> modifiers.any { this.modifiers.any { modifier -> modifier == it } }
        }

    override fun hasModifiers(): Boolean = modifiers.isNotEmpty()
}
