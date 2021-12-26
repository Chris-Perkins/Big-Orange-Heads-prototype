package effects.decorators

import effects.wishes.AddGold
import gamestate.GameState
import gamestate.GameStateChange
import gamestate.Player
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AddGoldPerBaseGoldEffectDecoratorTest {
    private val dummyPlayer = Player(name = "dum")
    private val dummyGameState = GameState(
        currentTurn = 1,
        players = listOf(dummyPlayer)
    )

    @Test
    fun `adds additional gold modifier modifies game state additionalGoldPerBaseGoldGain`()
    {
        val goldAmountToAdd = 5L
        val multiplier = 2L

        val decoratedEffect = AddGoldPerBaseGoldDecorator(
            baseEffect = AddGold(dummyPlayer, goldAmountToAdd),
            additionalGoldPerBaseGold = multiplier,
        )
        val expectedResult = GameStateChange(baseGoldGain = goldAmountToAdd, additionalGoldPerBaseGoldGain = multiplier)
        assertEquals(expectedResult, decoratedEffect.getGameStateChange(dummyGameState))
    }

    @Test
    fun `chained adds additional gold per base sums additional gold gain result`()
    {
        val goldAmountToAdd = 5L
        val multiplier = 3L

        val chainedEffect = AddGoldPerBaseGoldDecorator(
            baseEffect = AddGoldPerBaseGoldDecorator(
                baseEffect = AddGold(dummyPlayer, goldAmountToAdd),
                additionalGoldPerBaseGold = multiplier,
            ),
            additionalGoldPerBaseGold = multiplier
        )
        val expectedResult =
            GameStateChange(baseGoldGain = goldAmountToAdd, additionalGoldPerBaseGoldGain = multiplier * 2)
        assertEquals(expectedResult, chainedEffect.getGameStateChange(dummyGameState))
    }
}