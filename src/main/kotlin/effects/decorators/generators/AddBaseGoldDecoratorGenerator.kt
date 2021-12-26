package effects.decorators.generators

import effects.Effect
import effects.EffectDecoratorGeneratorPriority
import effects.EffectDecorator
import effects.decorators.AddBaseGoldEffectDecorator

class AddBaseGoldDecoratorGenerator(
    override val priority: EffectDecoratorGeneratorPriority = EffectDecoratorGeneratorPriority.ADD,
    private val addAmount: Long,
    expireOnTurn: Int
) : TurnBasedExpirationEffectDecoratorGenerator(
    expireOnTurn = expireOnTurn
) {
    override fun generateEffectDecorator(baseEffect: Effect): EffectDecorator =
        AddBaseGoldEffectDecorator(baseEffect = baseEffect, addAmount = addAmount)
}