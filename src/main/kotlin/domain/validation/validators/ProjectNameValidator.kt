package domain.validation.validators
import domain.validation.EcosystemValidator
import domain.model.exception.ProjectException

class ProjectNameValidator : EcosystemValidator<String> {
    override fun validate(data: String): String {
        val value = data.trim()
        if (value.isEmpty()) {
            throw ProjectException.EmptyProjectNameException()
        }
        if (value.length < 3) {
            throw ProjectException.InvalidProjectNameLengthException()
        }
        return value
    }
}