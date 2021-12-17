package gamestate

import effects.EffectModifier

class Player(
    val name: String,
    var isWearingBigOrangeHead: Boolean = false,
    val playerEffectModifiers: List<EffectModifier> = ArrayList()
) {
}