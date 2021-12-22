package effects

enum class EffectRarity(val baseWeightOfDrawing: Long) {
    LEGENDARY(1L),
    RARE(2L),
    COMMON(3L),
}