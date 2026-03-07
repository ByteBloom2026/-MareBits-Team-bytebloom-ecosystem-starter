package domain.validation.validators
import domain.validation.ValidationResult
import domain.validation.Validator
class ScoreValidator(
    private val min: Double = 0.0,
    private val max: Double = 100.0
) : Validator<Double> {
    override fun validate(data: Double): ValidationResult<Double> {
        if (data.isNaN() || data.isInfinite()) {
            return ValidationResult.failure("score", "Score must be a valid number.")
        }
        if (data < min || data > max) {
            return ValidationResult.failure("score", "Score must be between $min and $max.")
        }
        return ValidationResult.success(data)
    }
}