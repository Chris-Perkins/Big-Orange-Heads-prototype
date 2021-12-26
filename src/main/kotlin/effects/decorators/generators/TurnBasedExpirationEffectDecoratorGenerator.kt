package effects.decorators.generators

import effects.EffectDecoratorGenerator
import gamestate.GameManager
import gamestate.GameState

abstract class TurnBasedExpirationEffectDecoratorGenerator(
    private val expireOnTurn: Int,
): EffectDecoratorGenerator {
    override fun isExpired(gameState: GameState): Boolean =
        gameState.currentTurn >= expireOnTurn
}