package gamestate

import effects.EffectDecorator
import effects.EffectDecoratorGenerator

class Player(
    val name: String,
    val playerDecoratorGenerators: MutableList<EffectDecoratorGenerator> = ArrayList(),
    private var isWearingBigOrangeHead: Boolean = false,
) {
    fun getIsWearingBigOrangeHead(): Boolean = isWearingBigOrangeHead
}