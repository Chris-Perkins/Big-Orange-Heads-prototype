package effects.wishes

import effects.Effect
import effects.EffectModifierPriority
import effects.EffectRarity
import effects.TurnBasedModifier

class AddToNextGoldAmount(
    endingTurn: Int,
    private val addAmount: Long,
    override val rarity: EffectRarity,
): TurnBasedModifier(
    endingTurn = endingTurn
) {
    override val priority: EffectModifierPriority = EffectModifierPriority.ADD

    override fun addEffect(baseEffect: Effect): Effect {
        baseEffect.baseGold += addAmount
        return baseEffect
    }
}