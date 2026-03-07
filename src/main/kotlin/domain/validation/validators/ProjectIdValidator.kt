package domain.validation.validators
import domain.validation.ValidationResult
import domain.validation.Validator
class ProjectIdValidator : Validator<String> {
    override fun validate(data: String): ValidationResult<String> {
        val value = data.trim()
        if (value.isEmpty()) {
            return ValidationResult.failure("projectId", "Project id is required.")
        }
        if (!value.startsWith("p", ignoreCase = true)) {
            return ValidationResult.failure("projectId",
                "Project id must start with 'p'.")
        }
        val numberPart = value.drop(1)
        if (numberPart.length != 2 || !numberPart.all { it.isDigit() }) {
            return ValidationResult.failure("projectId",
                "Project id must have 2 digits after 'p'.")
        }
        return ValidationResult.success(value.lowercase())
    }
}