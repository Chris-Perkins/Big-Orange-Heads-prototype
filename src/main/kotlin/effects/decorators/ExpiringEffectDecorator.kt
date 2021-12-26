package effects.decorators

import effects.Effect
import effects.EffectDecorator
import gamestate.GameState
import gamestate.GameStateChange
import gamestate.Player

/**
 * Decorates an effect so that it expires on or after the input turn count.
 */
class ExpiringEffectDecorator(
    override val baseEffect: Effect,
    private val expiresOnTurnNumber: Int
): EffectDecorator {
    override val sourcePlayer: Player = baseEffect.sourcePlayer

    override fun getGameStateChange(gameState: GameState): GameStateChange
        = baseEffect.getGameStateChange(gameState)

    override fun isExpired(gameState: GameState): Boolean
        = gameState.currentTurn >= expiresOnTurnNumber
}