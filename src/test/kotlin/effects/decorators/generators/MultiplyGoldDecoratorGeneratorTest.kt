package effects.decorators.generators

import effects.wishes.AddGold
import gamestate.GameManager
import gamestate.GameStateChange
import gamestate.Player
import org.junit.jupiter.api.Test

class MultiplyGoldDecoratorGeneratorTest {
    private val dummyGameManager = GameManager()
    private val dummyPlayer = Player(name = "dum")

    @Test
    fun `add gold decorator is generated appropriately`() {
        val baseAmount = 2L
        val multipliedAmount = 5L
        val baseEffect = AddGold(
            sourcePlayer = dummyPlayer,
            goldAmount = baseAmount,
        )
        val generator = MultiplyGoldDecoratorGenerator(multiplier = multipliedAmount, expireOnTurn = 1)
        val generatedEffect = generator.generateEffectDecorator(baseEffect)

        val result = generatedEffect.getGameStateChange(dummyGameManager)
        val expectedResult = GameStateChange(goldGain = baseAmount * multipliedAmount)
        assert(expectedResult == result)
    }
}