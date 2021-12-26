package gamestate

import effects.EffectDecoratorGenerator

/**
 * Represents a change in the game state
 */
data class GameStateChange(
    val baseGoldGain: Long? = null,
    val additionalGoldPerBaseGoldGain: Long = 0,
    val addedGameEffectDecorators: List<EffectDecoratorGenerator> = emptyList()
) {
    fun getTotalGoldGain(): Long {
        val baseGoldGain = baseGoldGain ?: return 0
        return baseGoldGain + (baseGoldGain * additionalGoldPerBaseGoldGain)
    }
}