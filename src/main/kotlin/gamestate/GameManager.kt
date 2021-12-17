package gamestate

import effects.EffectModifier
import effects.generators.EffectGenerator

class GameManager(
    private val activeWishGeneratorModifiers: MutableList<EffectGenerator> = ArrayList(),
    private val activeWishModifiers: MutableList<EffectModifier> = ArrayList(),
    private var goldAmount: Long = 0,
    private var currentTurn: Int = 1,
) {
    fun getCurrentTurn(): Int = currentTurn

    // Temporary thing; typically turns would be handled by the gamemanager itself but that's not real rn
    fun incrementTurnCount() = currentTurn++

    fun getGoldAmount(): Long = goldAmount
    fun addGoldAmount(amountToAdd: Long)
    {
        goldAmount = maxOf(0, goldAmount + amountToAdd)
    }

    fun addWishGeneratorModifier(wishGeneratorModifier: EffectGenerator) =
        activeWishGeneratorModifiers.add(wishGeneratorModifier)

    fun addWishModifier(wishModifier: EffectModifier) =
        activeWishModifiers.add(wishModifier)
}