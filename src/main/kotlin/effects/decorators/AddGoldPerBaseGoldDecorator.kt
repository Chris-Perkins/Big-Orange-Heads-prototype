package effects.decorators

import effects.Effect
import effects.EffectDecorator
import gamestate.GameState
import gamestate.GameStateChange
import gamestate.Player

class AddGoldPerBaseGoldDecorator(
    override val baseEffect: Effect,
    private val additionalGoldPerBaseGold: Long,
): EffectDecorator {
    override val sourcePlayer: Player = baseEffect.sourcePlayer

    override fun getGameStateChange(gameState: GameState): GameStateChange {
        val baseChange = baseEffect.getGameStateChange(gameState)
        return baseChange.copy(
            additionalGoldPerBaseGoldGain = baseChange.additionalGoldPerBaseGoldGain + additionalGoldPerBaseGold
        )
    }

    override fun isExpired(gameState: GameState): Boolean =
        baseEffect.isExpired(gameState)
}