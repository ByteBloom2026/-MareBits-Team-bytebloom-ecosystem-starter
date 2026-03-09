package domain.validation.validators
import domain.model.exception.EmptyMenteeNameException
import domain.model.exception.NotCapitalizedNameException
import domain.validation.EcosystemValidator
import domain.validation.ValidationResult
import domain.validation.Validator
class MenteeNameValidator : EcosystemValidator<String> {
    override fun validate(data: String): Result<String> {
        val value = data.trim()
        if (value.isEmpty()) {
            return Result.failure(EmptyMenteeNameException())
        }
        if (!value[0].isUpperCase()) {
            return Result.failure(NotCapitalizedNameException())
        }
        return Result.success(value)
    }
}



