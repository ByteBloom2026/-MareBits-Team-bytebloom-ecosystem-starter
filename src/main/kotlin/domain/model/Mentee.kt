package domain.model
import domain.validation.validators.*

data class Mentee  private constructor(
    val id: String,
    val name: Result<String>,
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
        ): Mentee {
            val menteeId = menteeIdValidator.validate(id)
            val nameName = menteeNameValidator.validate(name)
            val teamId = teamIdValidator.validate(teamId)
            return  Mentee(
                id = menteeId,
                name = nameName,
                teamId = teamId
            )

        }
    }
}


