package data.repository
import data.EcoSystemDataSource
import data.repository.mappers.toDomain
import domain.model.Project
import domain.validation.ValidationResult
class ProjectRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : ProjectRepository {
    override fun getAllProjects(): Result<List<Project>> {
        val rows = dataSource.getProjects().getOrElse { return Result.failure(it) }

        val out = mutableListOf<Project>()
        for (row in rows) {
            when (val mapped = row.toDomain()) {
                is ValidationResult.Success -> out.add(mapped.data)
                is ValidationResult.Failure -> { }
            }
        }
        return Result.success(out)
    }

    override fun getProjectByTeamId(teamId: String): Result<Project?> {
        val row = dataSource.getProjectByTeamId(teamId).getOrElse { return Result.failure(it) }
            ?: return Result.success(null)

        return when (val mapped = row.toDomain()) {
            is ValidationResult.Success -> Result.success(mapped.data)
            is ValidationResult.Failure -> Result.success(null)
        }
    }
}
