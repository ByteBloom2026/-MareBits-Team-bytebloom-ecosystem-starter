package domain.validation.validators
import domain.validation.EcosystemValidator
import domain.model.exception.*

class SubmissionIdValidator : EcosystemValidator<String> {
    private val idRegex = Regex("^sub\\d{3}$", RegexOption.IGNORE_CASE)
    override fun validate(data: String): String {
    val value = data.trim()
    if (value.isEmpty()) {
        throw SubmissionException.EmptySubmissionIdException()
    }
    if (!idRegex.matches(value)) {
        throw SubmissionException.InvalidSumbmissionIdException()
    }
    return  value.lowercase()
}

}

