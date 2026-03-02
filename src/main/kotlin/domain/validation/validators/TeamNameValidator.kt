package domain.validation.validators
import domain.validation.ValidationResult
import domain.validation.Validator

class TeamNameValidator: Validator<String> {
    private val minLength:Int=3
    override fun validate(data: String): ValidationResult<String> {
        val value = data.trim()
        if(value.isEmpty())
            return ValidationResult.failure("The team name"," cannot be empty.")
        if(value.length<minLength)
            return ValidationResult.failure("The team name ","is too short; it must be at least 3 letters long.")
        return ValidationResult.success(value)

    }
}