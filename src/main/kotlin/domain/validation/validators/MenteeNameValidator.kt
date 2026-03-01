package domain.validation.validators
import domain.validation.ValidationResult
import domain.validation.Validator
class MenteeNameValidator : Validator<String> {
    override fun validate(data: String): ValidationResult<String> {
        val value = data.trim()
        if (value.isEmpty()) {
            return ValidationResult.failure(field = "menteeName", message = "cannot be empty.")
        }
        if (!value[0].isUpperCase()) {
            return ValidationResult.failure(field = "menteeName", message = "The first letter must be capitalized.")
        }
        return ValidationResult.success(data = value)
    }
}



