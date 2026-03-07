package domain.validation.validators

import domain.model.exception.EmptyTeamNameException
import domain.model.exception.InvalidTeamNameLengthException
import domain.validation.EcosystemValidator

class TeamNameValidator : EcosystemValidator<String> {
    private val minLength: Int = 3
    override fun validate(data: String): Result<String> {
        val value = data.trim()
        if (value.isEmpty())
            return Result.failure(EmptyTeamNameException())
        if (value.length < minLength)
            return Result.failure(InvalidTeamNameLengthException())
        return Result.success(value)
    }
}