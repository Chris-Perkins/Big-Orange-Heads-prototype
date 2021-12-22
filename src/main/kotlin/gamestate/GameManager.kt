package gamestate

import effects.Effect
import effects.EffectDecoratorGenerator

class GameManager(
    private val numberOfTurns: Int = DEFAULT_NUM_TURNS,
    private val activeEffectDecoratorGenerators: MutableList<EffectDecoratorGenerator> = ArrayList(),
    private val activeEffects: MutableList<Effect> = ArrayList(),
    private var goldAmount: Long = 0,
    private var currentTurn: Int = 1,
) {
    companion object {
        private const val DEFAULT_NUM_TURNS = 10
    }

    fun getCurrentTurn(): Int = currentTurn
    fun getGoldAmount(): Long = goldAmount

    fun addEffect(effect: Effect) = activeEffects.add(effect)
    fun addEffectDecoratorGenerator(effectDecoratorGenerator: EffectDecoratorGenerator) =
        activeEffectDecoratorGenerators.add(effectDecoratorGenerator)

    /**
     * Plays the game, performing turns until the game is over.
     */
    fun playGame() {
        while (currentTurn <= numberOfTurns || isPlayerWinGameState() || isGenieWinGameState()) {
            performTurn()
            currentTurn++
        }

        if (isPlayerWinGameState()) {
            println("players win!")
        } else {
            println("the genie wins!")
        }
    }

    private fun performTurn() {
        removeExpiredEffects()

        requestPlayerCardChoices()
        requestPlayerWishChoices()
        requestGenieTwistChoice()

        val turnGameChange = getGameStateChange()
        applyGameStateChange(turnGameChange)
    }

    private fun removeExpiredEffects() {
        activeEffectDecoratorGenerators.removeAll { e -> e.isExpired(this) }
        activeEffects.removeAll { e -> e.isExpired(this) }
    }

    private fun requestPlayerCardChoices() {}
    private fun requestPlayerWishChoices() {}
    private fun requestGenieTwistChoice() {}

    /**
     * Gets the game's state change based on all active effects and their decorators
     */
    private fun getGameStateChange(): GameStateChange {
        val sortedDecoratorGenerators = activeEffectDecoratorGenerators.sortedBy { dg -> dg.priority.sortOrder }
        val totalOutcome =
            activeEffects.map { baseEffect -> getGameStateChangeForEffect(baseEffect, sortedDecoratorGenerators) }
        val combinedOutcome = totalOutcome.reduce { v1, v2 -> v1 + v2 }
        return combinedOutcome
    }

    /**
     * Runs all decorator generators on the input effect, and outputs the resulting chained outcome effect
     */
    private fun getGameStateChangeForEffect(
        effect: Effect,
        sortedEffectDecoratorGenerators: List<EffectDecoratorGenerator>,
    ): GameStateChange {
        var outcomeEffect = effect
        for (decorator in sortedEffectDecoratorGenerators) {
            outcomeEffect = decorator.generateEffectDecorator(effect)
        }
        return outcomeEffect.getGameStateChange(this)
    }

    private fun applyGameStateChange(gameStateChange: GameStateChange) {
        goldAmount += (gameStateChange.playerGoldGain ?: 0)
    }

    private fun isPlayerWinGameState(): Boolean = false
    private fun isGenieWinGameState(): Boolean = false
}