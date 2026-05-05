package data.repository
import domain.model.Project
interface ProjectRepository {
    suspend fun getAllProjects(): Result<List<Project>>
    suspend fun getProjectByTeamId(teamId: String): Result<Project?>
}