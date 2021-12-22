package effects

import effects.wishes.AddGold
import effects.decorators.AddGoldEffectDecorator
import effects.decorators.MultiplyGoldEffectDecorator
import gamestate.GameManager
import org.junit.jupiter.api.Test

class EffectModifiersTest {
    @Test
    fun `multiply gold modifier multiplies base gold`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)

        val goldAmountToAdd = 5L
        val multiplier = 2L
        val result = MultiplyGoldEffectDecorator(
            baseEffect = AddGold(goldAmountToAdd, 1),
            multiplier = multiplier,
        ).getGameStateChange(dummyGameManager)

        assert(goldAmountToAdd * multiplier == result.playerGoldGain)
    }

    @Test
    fun `chain multiplication effects stack`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)

        val goldAmountToAdd = 5L
        val multiplier = 2L
        val chainedResult = MultiplyGoldEffectDecorator(
            baseEffect = MultiplyGoldEffectDecorator(
                baseEffect = AddGold(goldAmountToAdd, 1),
                multiplier = multiplier
            ),
            multiplier = multiplier
        ).getGameStateChange(dummyGameManager)

        assert(goldAmountToAdd * multiplier * multiplier == chainedResult.playerGoldGain)
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
                baseEffect = AddGold(baseGold, 1),
                addAmount = amountToAddNextValue
            ), multiplier = multiplier
        ).getGameStateChange(dummyGameManager)

        assert((baseGold + amountToAddNextValue) * multiplier == result.playerGoldGain)
    }

    @Test
    fun `add to next gold - multiply - base is (base * multiply + added amount)`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)

        val baseGold = 1L
        val amountToAddNextValue = 20L
        val multiplier = 2L
        val result = AddGoldEffectDecorator(
            baseEffect = MultiplyGoldEffectDecorator(
                baseEffect = AddGold(baseGold, 1),
                multiplier = multiplier
            ),
            addAmount = amountToAddNextValue
        ).getGameStateChange(dummyGameManager)

        assert((baseGold * multiplier) + amountToAddNextValue == result.playerGoldGain)
    }
}