package effects.decorators.generators

import effects.wishes.AddGold
import gamestate.GameManager
import org.junit.jupiter.api.Test

class MultiplyGoldDecoratorGeneratorTest {
    val dummyGameManager = GameManager()

    @Test
    fun `add gold decorator is generated appropriately`() {
        val baseAmount = 2L
        val multipliedAmount = 5L
        val baseEffect = AddGold(baseAmount, 1)
        val generator = MultiplyGoldDecoratorGenerator(multiplier = multipliedAmount, expireOnTurn = 1)
        val generatedEffect = generator.generateEffectDecorator(baseEffect)

        val generatedGoldGain = generatedEffect.getGameStateChange(dummyGameManager).playerGoldGain
        assert(baseAmount * multipliedAmount == generatedGoldGain)
    }
}