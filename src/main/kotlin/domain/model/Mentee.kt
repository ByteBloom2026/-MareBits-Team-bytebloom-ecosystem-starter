package domain.model
import domain.validation.validators.*
import domain.validation.ValidationResult

data class Mentee  private constructor(
    val id: String,
    val name: String,
    val teamId: String
) {

    companion object {
        val menteeNameValidator = MenteeNameValidator()
        val menteeIdValidator = MenteeIdValidator()
        val teamIdValidator = TeamIdValidator()
        fun create(
            id: String,
            name: String,
            teamId: String
        ): ValidationResult<Mentee> {
            val idResult = menteeIdValidator.validate(id)
            if (idResult is ValidationResult.Failure)
                return idResult
            val nameResult = menteeNameValidator.validate(name)
            if (nameResult is ValidationResult.Failure)
                return nameResult
            val teamResult = teamIdValidator.validate(teamId)
            if (teamResult is ValidationResult.Failure)
                return teamResult
            return ValidationResult.success(
                Mentee(
                    id = (idResult as ValidationResult.Success).data,
                    name = (nameResult as ValidationResult.Success).data,
                    teamId = (teamResult as ValidationResult.Success).data
                )
            )
        }
    }
}


