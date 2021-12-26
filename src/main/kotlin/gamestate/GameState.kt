package gamestate

import effects.Effect
import effects.EffectDecoratorGenerator

data class GameState(
    val currentTurn: Int = 1,
    val maxNumberOfTurns: Int = 10,
    val players: List<Player> = emptyList(),
    val gold: Long = 0,
    val activeEffects: List<Effect> = emptyList(),
    val activeEffectDecoratorGenerators: List<EffectDecoratorGenerator> = emptyList(),
)