package gamestate

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.expect

class GameStateChangeTest {
    @Nested
    inner class GetTotalGoldGain {
        @Test
        fun `base gold when additionalGoldPerBaseGold = 0`()
        {
            val baseGold = 1L
            val testInstance = GameStateChange(
                baseGoldGain = baseGold,
                additionalGoldPerBaseGoldGain = 0
            )
            assertEquals(baseGold, testInstance.getTotalGoldGain())
        }

        @Test
        fun `base gold x 3 when additionalGoldPerBaseGold = 2`()
        {
            val baseGold = 3L
            val testInstance = GameStateChange(
                baseGoldGain = baseGold,
                additionalGoldPerBaseGoldGain = 2
            )
            assertEquals(baseGold * 3, testInstance.getTotalGoldGain())
        }

        @Test
        fun `0 when baseGoldGain is null`()
        {
            val testInstance = GameStateChange(
                baseGoldGain = null,
                additionalGoldPerBaseGoldGain = 5
            )
            assertEquals(0, testInstance.getTotalGoldGain())
        }

        @Test
        fun `0 when baseGoldGain is -1`()
        {
            val testInstance = GameStateChange(
                baseGoldGain = null,
                additionalGoldPerBaseGoldGain = -1L
            )
            assertEquals(0, testInstance.getTotalGoldGain())
        }

        @Test
        fun `-1 x baseGoldGain when baseGoldGain is -2`()
        {
            val baseGold = 5L
            val testInstance = GameStateChange(
                baseGoldGain = baseGold,
                additionalGoldPerBaseGoldGain = -2L
            )
            assertEquals(-1 * baseGold, testInstance.getTotalGoldGain())
        }
    }
}