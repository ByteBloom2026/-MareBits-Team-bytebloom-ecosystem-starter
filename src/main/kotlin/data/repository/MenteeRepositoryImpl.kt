package data.repository
import domain.model.Mentee
import data.EcoSystemDataSource
import data.repository.mappers.toDomain
import domain.model.exception.DataAccessException
import data.datasource.model.exception.BlankFilePathException
import data.datasource.model.exception.EmptyColumnException
import data.datasource.model.exception.EmptyLineException
import data.datasource.model.exception.FileDoesNotExistException
import data.datasource.model.exception.FileIsEmptyException
import data.datasource.model.exception.InvalidColumnCountException
import data.datasource.model.exception.PathIsNotFileException
class MenteeRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : MenteeRepository {
    override fun getAllMentees(): Result<List<Mentee>> {
        return dataSource.getMentees().fold(
            onSuccess = { menteeRows ->
                Result.success(
                    menteeRows.map { menteeRow -> menteeRow.toDomain() }
                )
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    override fun getMenteeById(id: String): Result<Mentee?> {
        return dataSource.getMenteeById(id).fold(
            onSuccess = { menteeRow ->
                if (menteeRow == null) {
                    Result.success(null)
                } else {
                    Result.success(menteeRow.toDomain())
                }
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    override fun getMenteesByTeamId(teamId: String): Result<List<Mentee>> {
        return dataSource.getMenteesByTeamId(teamId).fold(
            onSuccess = { menteeRows ->
                Result.success(
                    menteeRows.map { menteeRow -> menteeRow.toDomain() }
                )
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    private fun mapToDomainException(exception: Throwable): Throwable {
        return when (exception) {
            is BlankFilePathException,
            is FileDoesNotExistException,
            is PathIsNotFileException,
            is FileIsEmptyException -> {
                DataAccessException.InvalidDataSourceException()
            }
            is InvalidColumnCountException,
            is EmptyLineException,
            is EmptyColumnException -> {
                DataAccessException.InvalidDataFormatException()
            }
            else -> {
                DataAccessException.InvalidDataSourceException()
            }
        }
    }
}


