package domain.validation.validators
import domain.validation.EcosystemValidator
import domain.model.exception.EmptyProjectNameException
import domain.model.exception.InvalidProjectNameLengthException
class ProjectNameValidator : EcosystemValidator<String> {
    override fun validate(data: String): String {
        val value = data.trim()
        if (value.isEmpty()) {
            throw EmptyProjectNameException()
        }
        if (value.length < 3) {
            throw InvalidProjectNameLengthException()
        }
        return value
    }
}