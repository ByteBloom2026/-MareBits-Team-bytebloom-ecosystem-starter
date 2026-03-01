package domain.model
import domain.validation.ValidationResult
import domain.validation.validators.ProjectNameValidator
import domain.validation.validators.TeamIdValidator
data class Project private constructor(
    val id  : String,
    val name :String,
    val assignedTeamId : String
){
    companion object {
        private val nameValidator = ProjectNameValidator()
        private val teamIdValidator = TeamIdValidator()
        fun create(id: String, name: String, assignedTeamId: String): ValidationResult<Project> {
            if (id.trim().isEmpty()) {
                return ValidationResult.failure("projectId", "Project id is required.")
            }
            val nameResult = nameValidator.validate(name)
            if (nameResult is ValidationResult.Failure) return nameResult
            val teamResult = teamIdValidator.validate(assignedTeamId)
            if (teamResult is ValidationResult.Failure) return teamResult
            return ValidationResult.success(
                Project(
                    id = id.trim().lowercase(),
                    name = name.trim(),
                    assignedTeamId = assignedTeamId.trim().lowercase()
                )
            )
        }
    }
}