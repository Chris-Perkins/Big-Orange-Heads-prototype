package effects.decorators

import effects.Effect
import effects.EffectDecorator
import gamestate.GameManager
import gamestate.GameStateChange

class AddGoldEffectDecorator(
    override val baseEffect: Effect,
    private val addAmount: Long,
): EffectDecorator {
    override fun getGameStateChange(gameManager: GameManager): GameStateChange {
        val baseChange = baseEffect.getGameStateChange(gameManager)
        val baseGoldChange = baseChange.playerGoldGain ?: return baseChange
        return baseChange.copy(playerGoldGain = baseGoldChange + addAmount)
    }

    override fun isExpired(gameManager: GameManager): Boolean =
        baseEffect.isExpired(gameManager)
}