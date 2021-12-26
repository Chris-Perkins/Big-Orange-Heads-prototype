package effects

import effects.wishes.AddGold
import effects.decorators.AddGoldEffectDecorator
import effects.decorators.MultiplyGoldEffectDecorator
import gamestate.GameManager
import gamestate.GameStateChange
import gamestate.Player
import org.junit.jupiter.api.Test

class EffectModifiersTest {
    private val dummyPlayer = Player(name = "lazy lion")
    @Test
    fun `multiply gold modifier multiplies base gold`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)
        val goldAmountToAdd = 5L
        val multiplier = 2L

        val result = MultiplyGoldEffectDecorator(
            baseEffect = AddGold(dummyPlayer, goldAmountToAdd),
            multiplier = multiplier,
        ).getGameStateChange(dummyGameManager)
        val expectedResult = GameStateChange(goldGain = goldAmountToAdd * multiplier)
        assert(expectedResult == result)
    }

    @Test
    fun `chain multiplication effects stack`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)

        val goldAmountToAdd = 5L
        val multiplier = 2L

        val result = MultiplyGoldEffectDecorator(
            baseEffect = MultiplyGoldEffectDecorator(
                baseEffect = AddGold(dummyPlayer, goldAmountToAdd),
                multiplier = multiplier
            ),
            multiplier = multiplier
        ).getGameStateChange(dummyGameManager)
        val expectedResult = GameStateChange(goldGain = goldAmountToAdd * multiplier * multiplier)
        assert(expectedResult == result)
    }

    @Test
    fun `multiply - add to next gold effect - base modifies base gold`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)

        val baseGold = 1L
        val amountToAddNextValue = 20L
        val multiplier = 2L

        val result = MultiplyGoldEffectDecorator(
            baseEffect = AddGoldEffectDecorator(
                baseEffect = AddGold(dummyPlayer, baseGold),
                addAmount = amountToAddNextValue
            ), multiplier = multiplier
        ).getGameStateChange(dummyGameManager)
        val expectedResult = GameStateChange(goldGain = (baseGold + amountToAddNextValue) * multiplier)
        assert(expectedResult == result)
    }

    @Test
    fun `add to next gold - multiply - base is (base x multiply + added amount)`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)

        val baseGold = 1L
        val amountToAddNextValue = 20L
        val multiplier = 2L

        val result = AddGoldEffectDecorator(
            baseEffect = MultiplyGoldEffectDecorator(
                baseEffect = AddGold(dummyPlayer, baseGold),
                multiplier = multiplier
            ),
            addAmount = amountToAddNextValue
        ).getGameStateChange(dummyGameManager)
        val expectedResult = GameStateChange(goldGain = (baseGold * multiplier) + amountToAddNextValue)
        assert(expectedResult == result)
    }
}