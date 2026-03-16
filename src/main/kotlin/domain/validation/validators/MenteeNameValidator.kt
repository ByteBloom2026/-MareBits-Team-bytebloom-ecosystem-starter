package domain.validation.validators
import domain.model.exception.MenteeException
import domain.validation.EcosystemValidator
import domain.model.exception.*

class MenteeNameValidator : EcosystemValidator<String> {
    override fun validate(data: String): String {
        val value = data.trim()
        if (value.isEmpty()) {
            throw MenteeException.EmptyMenteeNameException()
        }
        if (!value[0].isUpperCase()) {
            throw NotCapitalizedNameException()
        }
        return value

    }
}



