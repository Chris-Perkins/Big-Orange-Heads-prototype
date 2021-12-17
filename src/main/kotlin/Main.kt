import effects.EffectRarity
import effects.wishes.AddGold
import effects.wishes.MultiplyNextGold
import gamestate.GameManager
import gamestate.Player

fun main() {
    testAddGoldBase()
    testMultiplyGoldModifier()
    testChainedGoldModifier()
}

fun testAddGoldBase()
{
    val dummyGameManager = GameManager(currentTurn = 1)
    val dummyPlayer = Player(name = "Joe")

    val goldAmountToAdd = 5L
    AddGold(goldAmountToAdd).performEffect(dummyGameManager, dummyPlayer)

    val expected = goldAmountToAdd
    val actual = dummyGameManager.getGoldAmount()
    println("res: ${expected == actual} | expected = $actual | actual = $expected")
}

fun testMultiplyGoldModifier()
{
    val dummyGameManager = GameManager(currentTurn = 1)
    val dummyPlayer = Player(name = "Joe")

    val goldAmountToAdd = 5L
    val multiplier = 2L
    MultiplyNextGold(2, multiplyAmount = multiplier, EffectRarity.RARE)
        .addEffect(AddGold(goldAmountToAdd))
        .performEffect(dummyGameManager, dummyPlayer)

    val expected = goldAmountToAdd * multiplier
    val actual = dummyGameManager.getGoldAmount()
    println("res: ${expected == actual} | expected = $actual | actual = $expected")
}

fun testChainedGoldModifier()
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

    val expected = goldAmountToAdd * multiplier * multiplier
    val actual = dummyGameManager.getGoldAmount()
    println("res: ${expected == actual} | expected = $actual | actual = $expected")
}