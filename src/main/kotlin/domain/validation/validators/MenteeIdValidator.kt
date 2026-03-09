package domain.validation.validators
import domain.model.exception.*
import domain.validation.EcosystemValidator

class MenteeIdValidator : EcosystemValidator<String> {
    private val menteeIdRegex = Regex("^m\\d{3}$", RegexOption.IGNORE_CASE)
    override fun validate(data: String): String {
        val value = data.trim()
        if (value.isEmpty()) {
            throw EmptyMenteeNameException()
        }
        if (!menteeIdRegex.matches(value)) {
            throw InvalidMenteeNameException()
        }
        return value.lowercase()
    }
}