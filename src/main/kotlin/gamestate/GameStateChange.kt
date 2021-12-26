package gamestate

import effects.EffectDecoratorGenerator

/**
 * Represents a change in the game state
 */
data class GameStateChange(
    val goldGain: Long? = null,
    val addedGameEffectDecorators: List<EffectDecoratorGenerator> = emptyList()
)