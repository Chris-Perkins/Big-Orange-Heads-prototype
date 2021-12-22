package effects

enum class EffectDecoratorGeneratorPriority(val sortOrder: Long) {
    MULTIPLY(100),
    ADD(1);
}