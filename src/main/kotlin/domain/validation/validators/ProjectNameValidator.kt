package domain.validation.validators
import domain.validation.ValidationResult
import domain.validation.Validator
class ProjectNameValidator : Validator<String> {
    override fun validate(data: String): ValidationResult<String> {
        val value = data.trim()
        if (value.isEmpty()) {
            return ValidationResult.failure("projectName", "Project name is required.")
        }
        if (value.length < 3) {
            return ValidationResult.failure("projectName", "Project name must be at least 3 characters.")
        }
        return ValidationResult.success(value)
    }
}