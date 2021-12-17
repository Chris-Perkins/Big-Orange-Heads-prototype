package effects

enum class EffectModifierPriority(val priority: Long) {
    MULTIPLY(100),
    ADD(1);
}