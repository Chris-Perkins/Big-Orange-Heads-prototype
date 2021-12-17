import effects.EffectRarity
import effects.wishes.AddGold
import effects.wishes.AddToNextGoldAmount
import effects.wishes.MultiplyNextGold
import gamestate.GameManager
import gamestate.Player
import org.junit.jupiter.api.Test

class EffectModifiersTest {
    @Test
    fun `multiply gold modifier multiplies base gold`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)
        val dummyPlayer = Player(name = "Joe")

        val goldAmountToAdd = 5L
        val multiplier = 2L
        MultiplyNextGold(2, multiplyAmount = multiplier, EffectRarity.RARE)
            .addEffect(AddGold(goldAmountToAdd))
            .performEffect(dummyGameManager, dummyPlayer)

        assert(goldAmountToAdd * multiplier == dummyGameManager.getGoldAmount())
    }

    @Test
    fun `chain multiplication effects stack`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)
        val dummyPlayer = Player(name = "Joe")

        val goldAmountToAdd = 5L
        val multiplier = 2L
        MultiplyNextGold(2, multiplyAmount = multiplier, EffectRarity.RARE)
            .addEffect(
                MultiplyNextGold(2, multiplyAmount = multiplier, EffectRarity.RARE)
                    .addEffect(AddGold(goldAmountToAdd))
            )
            .performEffect(dummyGameManager, dummyPlayer)

        assert(goldAmountToAdd * multiplier * multiplier == dummyGameManager.getGoldAmount())
    }

    @Test
    fun `multiply - add to next gold effect - base modifies base gold`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)
        val dummyPlayer = Player(name = "Joe")

        val baseGold = 1L
        val amountToAddNextValue = 20L
        val multiplier = 2L
        MultiplyNextGold(2, multiplyAmount = multiplier, EffectRarity.RARE)
            .addEffect(
                AddToNextGoldAmount(2, addAmount = amountToAddNextValue, EffectRarity.RARE)
                    .addEffect(AddGold(baseGold))
            )
            .performEffect(dummyGameManager, dummyPlayer)

        assert((baseGold + amountToAddNextValue) * multiplier == dummyGameManager.getGoldAmount())
    }

    @Test
    fun `add to next gold - multiply - base is (base * multiply + added amount)`()
    {
        val dummyGameManager = GameManager(currentTurn = 1)
        val dummyPlayer = Player(name = "Joe")

        val baseGold = 1L
        val amountToAddNextValue = 20L
        val multiplier = 2L
        AddToNextGoldAmount(2, addAmount = amountToAddNextValue, EffectRarity.RARE)
            .addEffect(
                MultiplyNextGold(2, multiplyAmount = multiplier, EffectRarity.RARE)
                .addEffect(
                    AddGold(baseGold)
                )
            )
            .performEffect(dummyGameManager, dummyPlayer)

        assert((baseGold * multiplier) + amountToAddNextValue == dummyGameManager.getGoldAmount())
    }
}