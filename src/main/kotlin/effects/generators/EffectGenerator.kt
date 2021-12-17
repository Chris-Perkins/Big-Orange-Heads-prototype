package effects.generators

import effects.Effect
import gamestate.GameManager
import gamestate.Player

interface EffectGenerator {
    val priority: EffectGeneratorPriority

    fun getEffects(pool: List<Effect>, numEffects: Int, output: MutableList<Effect>)
    fun isDestroyable(gameManager: GameManager, player: Player): Boolean
}