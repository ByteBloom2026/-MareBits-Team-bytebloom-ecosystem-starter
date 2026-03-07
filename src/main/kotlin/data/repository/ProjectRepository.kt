package data.repository
import domain.model.Project
interface ProjectRepository {
    fun getAllProjects(): Result<List<Project>>
    fun getProjectByTeamId(teamId: String): Result<Project?>
}