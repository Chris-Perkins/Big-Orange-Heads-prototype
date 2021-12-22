package gamestate

import effects.EffectDecorator

class Player(
    val name: String,
    val playerEffectModifiers: MutableList<EffectDecorator> = ArrayList(),
    var isWearingBigOrangeHead: Boolean = false,
) {
}