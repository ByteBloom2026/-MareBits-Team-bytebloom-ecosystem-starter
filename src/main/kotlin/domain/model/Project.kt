package domain.model
import domain.validation.ValidationResult
import domain.validation.validators.ProjectNameValidator
import domain.validation.validators.TeamIdValidator
import domain.validation.validators.ProjectIdValidator
data class Project private constructor(
    val id  : String,
    val name :String,
    val assignedTeamId : String
) {
    companion object {
        private val idValidator = ProjectIdValidator()
        private val nameValidator = ProjectNameValidator()
        private val teamIdValidator = TeamIdValidator()
        fun create(id: String, name: String, assignedTeamId: String): ValidationResult<Project> {
            val idResult = idValidator.validate(id)
            if (idResult is ValidationResult.Failure) return idResult
            val nameResult = nameValidator.validate(name)
            if (nameResult is ValidationResult.Failure) return nameResult
            val teamResult = teamIdValidator.validate(assignedTeamId)
            if (teamResult is ValidationResult.Failure) return teamResult
            return ValidationResult.success(
                Project(
                    id = (idResult as ValidationResult.Success).data,
                    name = (nameResult as ValidationResult.Success).data,
                    assignedTeamId = (teamResult as ValidationResult.Success).data
                )
            )
        }
    }
}