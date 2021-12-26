package effects.decorators

import effects.wishes.AddGold
import gamestate.GameState
import gamestate.GameStateChange
import gamestate.Player
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AddBaseGoldEffectDecoratorTest {
    private val dummyPlayer = Player(name = "dum")
    private val dummyGameState = GameState(
        players = listOf(dummyPlayer)
    )

    @Test
    fun `add gold modifier adds to base gold`()
    {
        val baseAddAmount = 5L
        val decoratorAddAmount = 10L

        val decoratedEffect = AddBaseGoldEffectDecorator(
            baseEffect = AddGold(sourcePlayer = dummyPlayer, goldAmount = baseAddAmount),
            addAmount = decoratorAddAmount,
        )

        val expectedResult = GameStateChange(baseGoldGain = baseAddAmount + decoratorAddAmount)
        assertEquals(expectedResult, decoratedEffect.getGameStateChange(dummyGameState))
    }

    @Test
    fun `chained add gold modifier adds to base gold`()
    {
        val baseAddAmount = 5L
        val decoratorAddAmount = 10L

        val chainedEffect = AddBaseGoldEffectDecorator(
            baseEffect = AddBaseGoldEffectDecorator(
                baseEffect = AddGold(sourcePlayer = dummyPlayer, goldAmount = baseAddAmount),
                addAmount = decoratorAddAmount,
            ),
            addAmount = decoratorAddAmount
        )

        val expectedResult = GameStateChange(baseGoldGain = baseAddAmount + decoratorAddAmount * 2)
        assertEquals(expectedResult, chainedEffect.getGameStateChange(dummyGameState))
    }
}