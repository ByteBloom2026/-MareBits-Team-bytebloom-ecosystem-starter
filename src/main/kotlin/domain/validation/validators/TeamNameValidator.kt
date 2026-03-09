package domain.validation.validators

import domain.model.exception.EmptyTeamNameException
import domain.model.exception.InvalidTeamNameLengthException
import domain.validation.EcosystemValidator

class TeamNameValidator : EcosystemValidator<String> {
    private val minLength: Int = 3
    override fun validate(data: String): String {
        val trimmedTeamName = data.trim()
        if (trimmedTeamName.isEmpty())
            throw EmptyTeamNameException()
        if (trimmedTeamName.length < minLength)
            throw InvalidTeamNameLengthException()
        return trimmedTeamName
    }
}