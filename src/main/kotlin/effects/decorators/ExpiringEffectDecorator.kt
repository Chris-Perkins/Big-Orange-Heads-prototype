package effects.decorators

import effects.Effect
import effects.EffectDecorator
import gamestate.GameManager
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

    override fun getGameStateChange(gameManager: GameManager): GameStateChange
        = baseEffect.getGameStateChange(gameManager)

    override fun isExpired(gameManager: GameManager): Boolean
        = gameManager.getCurrentTurn() >= expiresOnTurnNumber
}