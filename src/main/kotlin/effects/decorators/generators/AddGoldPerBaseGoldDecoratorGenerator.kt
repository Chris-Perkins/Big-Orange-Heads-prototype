package effects.decorators.generators

import effects.Effect
import effects.EffectDecoratorGeneratorPriority
import effects.EffectDecorator
import effects.decorators.AddGoldPerBaseGoldDecorator

class AddGoldPerBaseGoldDecoratorGenerator(
    override val priority: EffectDecoratorGeneratorPriority = EffectDecoratorGeneratorPriority.MULTIPLY,
    private val additionalGoldPerBaseGold: Long,
    expireOnTurn: Int
) : TurnBasedExpirationEffectDecoratorGenerator(
    expireOnTurn = expireOnTurn
) {
    override fun generateEffectDecorator(baseEffect: Effect): EffectDecorator =
        AddGoldPerBaseGoldDecorator(baseEffect = baseEffect, additionalGoldPerBaseGold = additionalGoldPerBaseGold)
}