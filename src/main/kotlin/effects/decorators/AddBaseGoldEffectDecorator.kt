package effects.decorators

import effects.Effect
import effects.EffectDecorator
import gamestate.GameState
import gamestate.GameStateChange
import gamestate.Player

class AddBaseGoldEffectDecorator(
    override val baseEffect: Effect,
    private val addAmount: Long,
): EffectDecorator {
    override val sourcePlayer: Player = baseEffect.sourcePlayer

    override fun getGameStateChange(gameState: GameState): GameStateChange {
        val baseChange = baseEffect.getGameStateChange(gameState)
        val baseGoldChange = baseChange.baseGoldGain ?: return baseChange
        return baseChange.copy(baseGoldGain = baseGoldChange + addAmount)
    }

    override fun isExpired(gameState: GameState): Boolean =
        baseEffect.isExpired(gameState)
}