package domain.model
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
        fun create(id: String, name: String, assignedTeamId: String): Project{
            val validProjectId = idValidator.validate(id)
            val validNameproject = nameValidator.validate(name)
            val validTeamId = teamIdValidator.validate(assignedTeamId)
            return Project(
                id = validProjectId,
                name = validNameproject,
                assignedTeamId = validTeamId
            )
        }
    }
}