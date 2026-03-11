package domain.validation.validators
import domain.validation.EcosystemValidator
import domain.model.exception.ProjectException

class ProjectIdValidator : EcosystemValidator<String> {
    override fun validate(data: String): String {
        val value = data.trim()
        if (value.isEmpty()) {
            throw ProjectException.InvalidProjectIdException()
        }
        if (!value.startsWith("p", ignoreCase = true)) {
            throw ProjectException.InvalidProjectIdException()
        }
        val numberPart = value.drop(1)
        if (numberPart.length != 2 || !numberPart.all { it.isDigit() }) {
            throw ProjectException.InvalidProjectIdException()
        }
        return value.lowercase()
    }
}