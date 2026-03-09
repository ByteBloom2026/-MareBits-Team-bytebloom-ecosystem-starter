package domain.validation.validators
import domain.validation.EcosystemValidator
import domain.model.exception.InvalidProjectIdException
class ProjectIdValidator : EcosystemValidator<String> {
    override fun validate(data: String): String {
        val value = data.trim()
        if (value.isEmpty()) {
            throw InvalidProjectIdException()
        }
        if (!value.startsWith("p", ignoreCase = true)) {
            throw InvalidProjectIdException()
        }
        val numberPart = value.drop(1)
        if (numberPart.length != 2 || !numberPart.all { it.isDigit() }) {
            throw InvalidProjectIdException()
        }
        return value.lowercase()
    }
}