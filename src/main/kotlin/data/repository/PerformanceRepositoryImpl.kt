package data.repository
import domain.model.PerformanceSubmission
import data.EcoSystemDataSource
import data.repository.mappers.toDomain
import data.datasource.model.exception.BlankFilePathException
import data.datasource.model.exception.EmptyColumnException
import data.datasource.model.exception.EmptyLineException
import data.datasource.model.exception.FileDoesNotExistException
import data.datasource.model.exception.FileIsEmptyException
import data.datasource.model.exception.InvalidColumnCountException
import data.datasource.model.exception.PathIsNotFileException
import domain.model.exception.DataAccessException

class PerformanceRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : PerformanceRepository {
    override fun getAllPerformance(): Result<List<PerformanceSubmission>> {
        return dataSource.getPerformances().fold(
            onSuccess = { performanceRows ->
                Result.success(
                    performanceRows.map { row -> row.toDomain() }
                )
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    override fun getPerformanceByMenteeId(menteeId: String): Result<List<PerformanceSubmission>> {
        return dataSource.getPerformanceByMenteeId(menteeId).fold(
            onSuccess = { performanceRows ->
                Result.success(
                    performanceRows.map { row -> row.toDomain() }
                )
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
