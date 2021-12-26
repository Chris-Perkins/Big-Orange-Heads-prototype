package effects.wishes

import gamestate.GameState
import gamestate.GameStateChange
import gamestate.Player
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AddGoldTest {
    private val dummyPlayer = Player(name = "dum")
    private val dummyGameState = GameState(
        players = listOf(dummyPlayer)
    )

    @Test
    fun `outputs gold gain GameStateChange if positive`() {
        val amountToAdd = 5L
        val addGoldEffect = AddGold(sourcePlayer = dummyPlayer, goldAmount = amountToAdd)

        val expectedGameStateChange = GameStateChange(baseGoldGain = amountToAdd)
        assertEquals(expectedGameStateChange, addGoldEffect.getGameStateChange(dummyGameState))
    }

    @Test
    fun `outputs gold loss GameStateChange if negative`() {
        val amountToAdd = -5L
        val loseGoldEffect = AddGold(sourcePlayer = dummyPlayer, goldAmount = amountToAdd)

        val expectedGameStateChange = GameStateChange(baseGoldGain = amountToAdd)
        assertEquals(expectedGameStateChange, loseGoldEffect.getGameStateChange(dummyGameState))
    }
}