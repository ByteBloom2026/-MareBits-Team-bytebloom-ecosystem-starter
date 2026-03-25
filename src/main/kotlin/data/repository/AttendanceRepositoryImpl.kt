package data.repository
import data.EcoSystemDataSource
import domain.model.Attendance
import data.repository.mappers.toDomain
import data.datasource.model.exception.BlankFilePathException
import data.datasource.model.exception.EmptyColumnException
import data.datasource.model.exception.EmptyLineException
import data.datasource.model.exception.FileDoesNotExistException
import data.datasource.model.exception.FileIsEmptyException
import data.datasource.model.exception.InvalidColumnCountException
import data.datasource.model.exception.PathIsNotFileException
import domain.model.exception.DataAccessException
class AttendanceRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : AttendanceRepository {
    override fun getAllAttendance(): Result<List<Attendance>> {
        return dataSource.getAttendances().fold(
            onSuccess = { attendanceRows ->
                Result.success(
                    attendanceRows.map { row -> row.toDomain() }
                )
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    override fun getAttendanceByMenteeId(menteeId: String): Result<Attendance?> {
        return dataSource.getAttendanceByMenteeId(menteeId).fold(
            onSuccess = { attendanceRow ->
                if (attendanceRow == null) {
                    Result.success(null)
                } else {
                    Result.success(attendanceRow.toDomain())
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


