package effects

import gamestate.GameManager

interface EffectDecoratorGenerator {
    val priority: EffectDecoratorGeneratorPriority

    fun generateEffectDecorator(baseEffect: Effect): EffectDecorator
    fun isExpired(gameManager: GameManager): Boolean
}