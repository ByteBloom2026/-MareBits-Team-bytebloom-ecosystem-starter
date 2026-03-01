package domain.validation.validators
import domain.validation.ValidationResult
import domain.validation.Validator
class TeamIdValidator : Validator<String> {
    override fun validate(data: String): ValidationResult<String> {
        val value = data.trim()
        if (value.isEmpty()) {
            return ValidationResult.failure("teamId",
                "Team id is required.")
        }
        if (!value.first().isLetter()) {
            return ValidationResult.failure("teamId",
                "Team id must start with a letter.")
        }
        if (!value.all { it.isLetter() }) {
            return ValidationResult.failure("teamId",
                "Team id must contain letters only.")
        }
        return ValidationResult.success(value.lowercase())
    }
}