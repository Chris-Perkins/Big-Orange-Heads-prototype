package effects.decorators

import effects.Effect
import effects.EffectDecorator
import gamestate.GameManager
import gamestate.GameStateChange
import gamestate.Player

class AddGoldEffectDecorator(
    override val baseEffect: Effect,
    private val addAmount: Long,
): EffectDecorator {
    override val sourcePlayer: Player = baseEffect.sourcePlayer

    override fun getGameStateChange(gameManager: GameManager): GameStateChange {
        val baseChange = baseEffect.getGameStateChange(gameManager)
        val baseGoldChange = baseChange.goldGain ?: return baseChange
        return baseChange.copy(goldGain = baseGoldChange + addAmount)
    }

    override fun isExpired(gameManager: GameManager): Boolean =
        baseEffect.isExpired(gameManager)
}