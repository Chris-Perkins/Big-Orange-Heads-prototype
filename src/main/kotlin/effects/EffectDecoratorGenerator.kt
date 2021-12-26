package effects

import gamestate.GameState

interface EffectDecoratorGenerator {
    val priority: EffectDecoratorGeneratorPriority

    fun generateEffectDecorator(baseEffect: Effect): EffectDecorator
    fun isExpired(gameState: GameState): Boolean
}