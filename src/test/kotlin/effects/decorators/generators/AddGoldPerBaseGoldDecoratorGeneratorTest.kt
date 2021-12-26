package effects.decorators.generators

import effects.decorators.AddGoldPerBaseGoldDecorator
import effects.wishes.AddGold
import gamestate.GameState
import gamestate.Player
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AddGoldPerBaseGoldDecoratorGeneratorTest {
    private val dummyPlayer = Player(name = "dum")
    private val dummyGameState = GameState(
        players = listOf(dummyPlayer)
    )

    @Test
    fun `add gold decorator is generated appropriately`() {
        val baseAmount = 2L
        val multipliedAmount = 5L
        val baseEffect = AddGold(
            sourcePlayer = dummyPlayer,
            goldAmount = baseAmount,
        )
        val manuallyDecoratedEffect =
            AddGoldPerBaseGoldDecorator(baseEffect, additionalGoldPerBaseGold = multipliedAmount)
        val expectedResult = manuallyDecoratedEffect.getGameStateChange(dummyGameState)

        val generator =
            AddGoldPerBaseGoldDecoratorGenerator(additionalGoldPerBaseGold = multipliedAmount, expireOnTurn = 1)
        val generatedEffect = generator.generateEffectDecorator(baseEffect)
        val result = generatedEffect.getGameStateChange(dummyGameState)

        assertEquals(expectedResult, result)
    }
}