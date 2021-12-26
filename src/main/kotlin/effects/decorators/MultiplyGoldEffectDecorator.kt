package effects.decorators

import effects.Effect
import effects.EffectDecorator
import gamestate.GameManager
import gamestate.GameStateChange
import gamestate.Player

class MultiplyGoldEffectDecorator(
    override val baseEffect: Effect,
    private val multiplier: Long,
): EffectDecorator {
    override val sourcePlayer: Player = baseEffect.sourcePlayer

    override fun getGameStateChange(gameManager: GameManager): GameStateChange {
        val baseChange = baseEffect.getGameStateChange(gameManager)
        val baseGoldGain = baseChange.goldGain ?: return baseChange
        return baseChange.copy(goldGain = baseGoldGain * multiplier)
    }

    override fun isExpired(gameManager: GameManager): Boolean =
        baseEffect.isExpired(gameManager)
}