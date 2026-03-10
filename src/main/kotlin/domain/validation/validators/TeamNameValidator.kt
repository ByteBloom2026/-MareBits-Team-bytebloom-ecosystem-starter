package domain.validation.validators
import domain.model.exception.*
import domain.validation.EcosystemValidator

class TeamNameValidator : EcosystemValidator<String> {
    private val minLength: Int = 3
    override fun validate(data: String): String {
        val trimmedTeamName = data.trim()
        if (trimmedTeamName.isEmpty())
            throw SearchTeamException.EmptyTeamNameException()
        if (trimmedTeamName.length < minLength)
            throw SearchTeamException.InvalidTeamNameLengthException()
        return trimmedTeamName
    }
}