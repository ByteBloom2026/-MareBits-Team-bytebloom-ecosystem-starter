package data.repository
import data.EcoSystemDataSource
import data.repository.mappers.toDomain
import domain.model.Project
import data.datasource.model.exception.BlankFilePathException
import data.datasource.model.exception.EmptyColumnException
import data.datasource.model.exception.EmptyLineException
import data.datasource.model.exception.FileDoesNotExistException
import data.datasource.model.exception.FileIsEmptyException
import data.datasource.model.exception.InvalidColumnCountException
import data.datasource.model.exception.PathIsNotFileException
import domain.model.exception.DataAccessException

class ProjectRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : ProjectRepository {
    override fun getAllProjects(): Result<List<Project>> {
        return dataSource.getProjects().fold(
            onSuccess = { projectRows ->
                Result.success(
                    projectRows.map { row -> row.toDomain() }
                )
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    override fun getProjectByTeamId(teamId: String): Result<Project?> {
        return dataSource.getProjectByTeamId(teamId).fold(
            onSuccess = { projectRow ->
                if (projectRow == null) {
                    Result.success(null)
                } else {
                    Result.success(projectRow.toDomain())
                }
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    private fun mapToDomainException(exception: Throwable): Throwable {
        return DataAccessException.DataUnavailableException()
    }
}
