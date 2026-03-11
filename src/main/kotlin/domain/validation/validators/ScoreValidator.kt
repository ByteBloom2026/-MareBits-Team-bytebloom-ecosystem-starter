package domain.validation.validators
import domain.validation.EcosystemValidator
import domain.model.exception.*

class ScoreValidator(
    private val min: Double = 0.0,
    private val max: Double = 100.0) : EcosystemValidator<Double> {
    override fun validate(data: Double): Double {
        if (data.isNaN() || data.isInfinite()) {
            throw ScoreException.InvalidScoreException()
        }
        if (data < min || data > max) {
            throw ScoreException.ScoreOutOfRangeException(min,max)
        }
        return data
    }
}