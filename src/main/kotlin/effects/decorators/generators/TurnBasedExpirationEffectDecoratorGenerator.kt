package effects.decorators.generators

import effects.EffectDecoratorGenerator
import gamestate.GameManager

abstract class TurnBasedExpirationEffectDecoratorGenerator(
    private val expireOnTurn: Int,
): EffectDecoratorGenerator {
    override fun isExpired(gameManager: GameManager): Boolean =
        gameManager.getCurrentTurn() >= expireOnTurn
}