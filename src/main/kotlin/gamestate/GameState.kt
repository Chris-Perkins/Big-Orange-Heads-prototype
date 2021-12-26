package gamestate

import effects.Effect
import effects.EffectDecoratorGenerator

data class GameState(
    val currentTurn: Int = 1,
    val maxNumberOfTurns: Int = 10,
    val players: List<Player> = emptyList(),
    val currentGold: Long = 0,
    val totalGoldForGeniePayoff: Long = 450,
    val activeEffects: List<Effect> = emptyList(),
    val activeEffectDecoratorGenerators: List<EffectDecoratorGenerator> = emptyList(),
)