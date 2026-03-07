package domain.model
import domain.validation.validators.*
import domain.validation.ValidationResult

data class Team  private constructor(
    val id: String,
    val name: String,
    val mentorLead: String,
    val projects: Project? = null
){
    constructor(id: String,name: String,mentorLead: String,project: Project) : this(
        id= id,
        name=name,
        mentorLead=mentorLead,
        projects=project
    )
    companion object {
        val teamNameValidator=TeamNameValidator()
        val menteeNameValidator=MenteeNameValidator()

        fun create(id: String, name: String, mentorLead: String, projects: Project?): ValidationResult<Team> {
            val nameResult = teamNameValidator.validate(name)
            if (nameResult is ValidationResult.Failure)
                return nameResult
            val mentorResult = menteeNameValidator.validate(mentorLead)
            if (mentorResult is ValidationResult.Failure)
                return mentorResult
            return ValidationResult.success(
                Team(
                    id = id,
                    name = (nameResult as ValidationResult.Success).data,
                    mentorLead = (mentorResult as ValidationResult.Success).data,
                    projects = projects
                )
            )
        }
    }

}