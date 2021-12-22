package effects.decorators.generators

import effects.Effect
import effects.EffectDecoratorGeneratorPriority
import effects.EffectDecorator
import effects.decorators.MultiplyGoldEffectDecorator

class MultiplyGoldDecoratorGenerator(
    override val priority: EffectDecoratorGeneratorPriority = EffectDecoratorGeneratorPriority.MULTIPLY,
    private val multiplier: Long,
    expireOnTurn: Int
) : TurnBasedExpirationEffectDecoratorGenerator(
    expireOnTurn = expireOnTurn
) {
    override fun generateEffectDecorator(baseEffect: Effect): EffectDecorator =
        MultiplyGoldEffectDecorator(baseEffect = baseEffect, multiplier = multiplier)
}