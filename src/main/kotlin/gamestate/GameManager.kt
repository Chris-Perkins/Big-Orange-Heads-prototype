package gamestate

import effects.Effect
import effects.EffectDecoratorGenerator

class GameManager(
    private var gameState: GameState,
) {
    /**
     * Plays the game, performing turns until the game is over.
     */
    fun playGame() {
        while (gameState.currentTurn <= gameState.maxNumberOfTurns || isPlayerWinGameState() || isGenieWinGameState()) {
            performTurn()
            gameState.copy(currentTurn = gameState.currentTurn + 1)
        }

        if (isPlayerWinGameState()) {
            println("players win!")
        } else {
            println("the genie wins!")
        }
    }

    private fun performTurn() {
        requestPlayerCardChoices()
        requestPlayerWishChoices()
        requestGenieTwistChoice()

        removeExpiredEffectsAndDecorators()
        // Apply effects individually as effects may modify the effects of other effects
        for (effect in gameState.activeEffects) {
            val gameStateChangeForDecoratedEffect = getTotalGameStateChangeForEffect(effect)
            applyGameStateChange(gameStateChangeForDecoratedEffect)
        }
    }

    private fun removeExpiredEffectsAndDecorators() {
        gameState = gameState.copy(
            activeEffectDecoratorGenerators =
                gameState.activeEffectDecoratorGenerators.filter { dg -> !dg.isExpired(gameState) },
            activeEffects = gameState.activeEffects.filter { e -> !e.isExpired(gameState) },
            players = gameState.players.map { player ->
                player.copy(
                    playerDecoratorGenerators =
                        player.playerDecoratorGenerators.filter { dg -> dg.isExpired(gameState) }
                )
            },
        )
    }

    private fun requestPlayerCardChoices() {}
    private fun requestPlayerWishChoices() {}
    private fun requestGenieTwistChoice() {}

    /**
     * Gets the game's state change based on all active effects and their decorators
     */
    private fun getTotalGameStateChangeForEffect(effect: Effect): GameStateChange {
        val sortedDecoratorGenerators = getDecoratorGeneratorsForPlayer(effect.sourcePlayer)
        return getGameStateChangeForEffectAfterApplyingDecorators(effect, sortedDecoratorGenerators)
    }

    /**
     * Gets the decorator generators which should be applied to the effects of an individual player
     */
    private fun getDecoratorGeneratorsForPlayer(player: Player): List<EffectDecoratorGenerator>
        = gameState.activeEffectDecoratorGenerators + player.playerDecoratorGenerators

    /**
     * Runs all decorator generators on the input effect, and outputs the resulting chained outcome effect
     */
    private fun getGameStateChangeForEffectAfterApplyingDecorators(
        effect: Effect,
        sortedEffectDecoratorGenerators: List<EffectDecoratorGenerator>,
    ): GameStateChange {
        var outcomeEffect = effect
        for (decorator in sortedEffectDecoratorGenerators) {
            outcomeEffect = decorator.generateEffectDecorator(effect)
        }
        return outcomeEffect.getGameStateChange(gameState)
    }

    private fun applyGameStateChange(gameStateChange: GameStateChange) {
        gameState = gameState.copy(
            gold = gameState.gold + (gameStateChange.goldGain ?: 0),
            activeEffectDecoratorGenerators =
                gameState.activeEffectDecoratorGenerators + gameStateChange.addedGameEffectDecorators
        )
    }

    private fun isPlayerWinGameState(): Boolean = false
    private fun isGenieWinGameState(): Boolean = false
}