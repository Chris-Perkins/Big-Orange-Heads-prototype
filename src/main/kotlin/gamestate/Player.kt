package gamestate

import effects.EffectDecorator
import effects.EffectDecoratorGenerator

data class Player(
    val name: String,
    val playerDecoratorGenerators: List<EffectDecoratorGenerator> = ArrayList(),
    val isWearingBigOrangeHead: Boolean = false,
)