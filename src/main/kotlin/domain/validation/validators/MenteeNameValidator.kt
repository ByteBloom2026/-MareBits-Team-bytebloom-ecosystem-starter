package domain.validation.validators
import domain.model.exception.EmptyMenteeNameException
import domain.model.exception.NotCapitalizedNameException
import domain.validation.EcosystemValidator

class MenteeNameValidator : EcosystemValidator<String> {
    override fun validate(data: String): String {
        val value = data.trim()
        if (value.isEmpty()) {
            throw EmptyMenteeNameException()
        }
        if (!value[0].isUpperCase()) {
            throw NotCapitalizedNameException()
        }
        return value

    }
}



