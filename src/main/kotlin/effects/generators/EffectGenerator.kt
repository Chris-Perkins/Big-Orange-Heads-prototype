package effects.generators

import effects.Effect

interface EffectGenerator {
    val priority: EffectGeneratorPriority

    fun getEffects(pool: List<Effect>, numEffects: Int, output: MutableList<Effect>)
    fun isDestroyable(): Boolean
}