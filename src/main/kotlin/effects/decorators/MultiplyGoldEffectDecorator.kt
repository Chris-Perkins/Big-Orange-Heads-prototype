package effects.decorators

import effects.Effect
import effects.EffectDecorator
import gamestate.GameManager
import gamestate.GameStateChange

class MultiplyGoldEffectDecorator(
    override val baseEffect: Effect,
    private val multiplier: Long,
): EffectDecorator {
    override fun getGameStateChange(gameManager: GameManager): GameStateChange {
        val baseChange = baseEffect.getGameStateChange(gameManager)
        val baseGoldGain = baseChange.playerGoldGain ?: return baseChange
        return baseChange.copy(playerGoldGain = baseGoldGain * multiplier)
    }

    override fun isExpired(gameManager: GameManager): Boolean =
        baseEffect.isExpired(gameManager)
}