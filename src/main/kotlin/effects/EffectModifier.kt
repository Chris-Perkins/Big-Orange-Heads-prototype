package effects

import gamestate.GameManager
import gamestate.Player

interface EffectModifier {
    val rarity: EffectRarity
    val priority: EffectModifierPriority

    fun addEffect(baseEffect: Effect): Effect
    fun isModifierDestroyable(gameManager: GameManager, player: Player): Boolean
}