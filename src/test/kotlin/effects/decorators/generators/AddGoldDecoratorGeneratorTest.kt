package effects.decorators.generators

import effects.decorators.AddBaseGoldEffectDecorator
import effects.wishes.AddGold
import gamestate.GameState
import gamestate.Player
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
        val manuallyAppliedDecorator = AddBaseGoldEffectDecorator(baseEffect, addAmount = addedAmount)
        val expectedResult = manuallyAppliedDecorator.getGameStateChange(dummyGameState)

        val generator = AddBaseGoldDecoratorGenerator(addAmount = addedAmount, expireOnTurn = 1)
        val generatedEffect = generator.generateEffectDecorator(baseEffect)
        val result = generatedEffect.getGameStateChange(dummyGameState)

        assertEquals(expectedResult, result)
    }
}