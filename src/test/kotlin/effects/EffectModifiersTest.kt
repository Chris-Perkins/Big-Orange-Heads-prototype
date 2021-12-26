package effects

import effects.wishes.AddGold
import effects.decorators.AddGoldEffectDecorator
import effects.decorators.MultiplyGoldEffectDecorator
import gamestate.GameManager
import gamestate.GameState
import gamestate.GameStateChange
import gamestate.Player
import org.junit.jupiter.api.Test

class EffectModifiersTest {
    private val dummyPlayer = Player(name = "dum")
    private val dummyGameState = GameState(
        players = listOf(dummyPlayer)
    )

    @Test
    fun `multiply gold modifier multiplies base gold`()
    {
        val goldAmountToAdd = 5L
        val multiplier = 2L

        val result = MultiplyGoldEffectDecorator(
            baseEffect = AddGold(dummyPlayer, goldAmountToAdd),
            multiplier = multiplier,
        ).getGameStateChange(dummyGameState)
        val expectedResult = GameStateChange(goldGain = goldAmountToAdd * multiplier)
        assert(expectedResult == result)
    }

    @Test
    fun `chain multiplication effects stack`()
    {
        val goldAmountToAdd = 5L
        val multiplier = 2L

        val result = MultiplyGoldEffectDecorator(
            baseEffect = MultiplyGoldEffectDecorator(
                baseEffect = AddGold(dummyPlayer, goldAmountToAdd),
                multiplier = multiplier
            ),
            multiplier = multiplier
        ).getGameStateChange(dummyGameState)
        val expectedResult = GameStateChange(goldGain = goldAmountToAdd * multiplier * multiplier)
        assert(expectedResult == result)
    }

    @Test
    fun `multiply - add to next gold effect - base modifies base gold`()
    {
        val baseGold = 1L
        val amountToAddNextValue = 20L
        val multiplier = 2L

        val result = MultiplyGoldEffectDecorator(
            baseEffect = AddGoldEffectDecorator(
                baseEffect = AddGold(dummyPlayer, baseGold),
                addAmount = amountToAddNextValue
            ), multiplier = multiplier
        ).getGameStateChange(dummyGameState)
        val expectedResult = GameStateChange(goldGain = (baseGold + amountToAddNextValue) * multiplier)
        assert(expectedResult == result)
    }

    @Test
    fun `add to next gold - multiply - base is (base x multiply + added amount)`()
    {
        val baseGold = 1L
        val amountToAddNextValue = 20L
        val multiplier = 2L

        val result = AddGoldEffectDecorator(
            baseEffect = MultiplyGoldEffectDecorator(
                baseEffect = AddGold(dummyPlayer, baseGold),
                multiplier = multiplier
            ),
            addAmount = amountToAddNextValue
        ).getGameStateChange(dummyGameState)
        val expectedResult = GameStateChange(goldGain = (baseGold * multiplier) + amountToAddNextValue)
        assert(expectedResult == result)
    }
}