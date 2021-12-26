package effects.decorators.generators

import effects.wishes.AddGold
import gamestate.GameState
import gamestate.GameStateChange
import gamestate.Player
import org.junit.jupiter.api.Test

class AddGoldDecoratorGeneratorTest {
    private val dummyPlayer = Player(name = "dum")
    private val dummyGameState = GameState(
        players = listOf(dummyPlayer)
    )

    @Test
    fun `add gold decorator is generated appropriately`() {
        val baseAmount = 1L
        val addedAmount = 5L
        val baseEffect = AddGold(
            sourcePlayer = dummyPlayer,
            goldAmount = baseAmount,
        )
        val generator = AddGoldDecoratorGenerator(addAmount = addedAmount, expireOnTurn = 1)
        val generatedEffect = generator.generateEffectDecorator(baseEffect)

        val result = generatedEffect.getGameStateChange(dummyGameState)
        val expectedResult = GameStateChange(goldGain = baseAmount + addedAmount)
        assert(result == expectedResult)
    }
}