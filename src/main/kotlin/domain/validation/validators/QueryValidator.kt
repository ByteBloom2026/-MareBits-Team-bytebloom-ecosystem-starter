package domain.validation.validators
import domain.validation.ValidationResult
import domain.validation.Validator

class QueryValidator(
    private val minLength: Int = 2
) : Validator<String> {
    override fun validate(data: String): ValidationResult<String> {
        val value = data.trim()
        if (value.isEmpty()) {
            return ValidationResult.failure("query", "Search query cannot be empty.")
        }
        if (value.length < minLength) {
            return ValidationResult.failure("query", "Search query is too short.")
        }
        return ValidationResult.success(value)
    }
}