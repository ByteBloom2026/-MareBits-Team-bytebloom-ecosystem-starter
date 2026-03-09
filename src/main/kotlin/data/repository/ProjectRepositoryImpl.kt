package data.repository
import data.EcoSystemDataSource
import data.repository.mappers.toDomain
import domain.model.Project

class ProjectRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : ProjectRepository {
    override fun getAllProjects(): Result<List<Project>> {
        val rows = dataSource.getProjects()
            .getOrElse { return Result.failure(it) }
            .map { it.toDomain() }
        return Result.success(rows)
    }

    override fun getProjectByTeamId(teamId: String): Result<Project?> {
        val row = dataSource.getProjectByTeamId(teamId)
            .getOrElse { return Result.failure(it) }
            ?: return Result.success(null)
        val project=row.toDomain()
        return Result.success(project)
    }
}
