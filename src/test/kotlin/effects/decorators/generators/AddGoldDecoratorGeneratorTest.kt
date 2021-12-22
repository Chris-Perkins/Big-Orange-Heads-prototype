package effects.decorators.generators

import effects.EffectDecoratorGeneratorPriority
import effects.wishes.AddGold
import gamestate.GameManager
import org.junit.jupiter.api.Test

class AddGoldDecoratorGeneratorTest {
    val dummyGameManager = GameManager()

    @Test
    fun `add gold decorator is generated appropriately`() {
        val baseAmount = 1L
        val addedAmount = 5L
        val baseEffect = AddGold(baseAmount, 1)
        val generator = AddGoldDecoratorGenerator(addAmount = addedAmount, expireOnTurn = 1)
        val generatedEffect = generator.generateEffectDecorator(baseEffect)

        val generatedGoldGain = generatedEffect.getGameStateChange(dummyGameManager).playerGoldGain
        assert(baseAmount + addedAmount == generatedGoldGain)
    }
}