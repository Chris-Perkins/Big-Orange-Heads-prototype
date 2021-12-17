package effects.wishes

import effects.Effect
import effects.EffectModifierPriority
import effects.EffectRarity
import effects.TurnBasedModifier

class MultiplyNextGold(
    private val endingTurn: Int,
    private val multiplyAmount: Long,
    override val rarity: EffectRarity,
): TurnBasedModifier(
    endingTurn = endingTurn
) {
    override val priority: EffectModifierPriority = EffectModifierPriority.MULTIPLY

    override fun addEffect(baseEffect: Effect): Effect {
        baseEffect.baseGold *= multiplyAmount
        return baseEffect
    }
}