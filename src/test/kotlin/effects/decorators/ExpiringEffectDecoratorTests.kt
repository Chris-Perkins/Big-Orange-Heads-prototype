package effects.decorators

import effects.wishes.AddGold
import effects.wishes.AddGoldTest
import gamestate.GameState
import gamestate.Player
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ExpiringEffectDecoratorTests {
    private val dummyPlayer = Player(name = "dum")
    private val dummyGameState = GameState(
        currentTurn = 1,
        players = listOf(dummyPlayer)
    )

    @Test
    fun `not expired if turn not reached`() {
        val expirationTurn = 5
        val gameStateToTest = dummyGameState.copy(currentTurn = expirationTurn - 1)
        val baseEffect = AddGold(dummyPlayer, 0)

        assertFalse { baseEffect.isExpired(gameStateToTest) }

        val decoratedEffect = ExpiringEffectDecorator(baseEffect, expirationTurn)
        assertFalse { decoratedEffect.isExpired(gameStateToTest) }
    }

    @Test
    fun `expired if turn reached`() {
        val expirationTurn = 5
        val gameStateToTest = dummyGameState.copy(currentTurn = expirationTurn)
        val baseEffect = AddGold(dummyPlayer, 0)

        assertFalse { baseEffect.isExpired(gameStateToTest) }
        val decoratedEffect = ExpiringEffectDecorator(baseEffect, expirationTurn)
        assertTrue { decoratedEffect.isExpired(gameStateToTest) }
    }

    @Test
    fun `expired if turn reached exceeded`() {
        val expirationTurn = 5
        val gameStateToTest = dummyGameState.copy(currentTurn = expirationTurn + 1)
        val baseEffect = AddGold(dummyPlayer, 0)

        assertFalse { baseEffect.isExpired(gameStateToTest) }
        val decoratedEffect = ExpiringEffectDecorator(baseEffect, expirationTurn)
        assertTrue { decoratedEffect.isExpired(gameStateToTest) }
    }
}