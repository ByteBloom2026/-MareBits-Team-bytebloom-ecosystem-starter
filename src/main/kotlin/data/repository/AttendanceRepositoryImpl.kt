package data.repository
import data.EcoSystemDataSource
import domain.model.Attendance
import data.repository.mappers.toDomain


class AttendanceRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : AttendanceRepository {
    override fun getAllAttendance(): Result<List<Attendance>> {
        val rows = dataSource.getAttendances()
            .getOrElse { return Result.failure(it) }
            .map { it.toDomain() }
        return Result.success(rows)
    }
    override fun getAttendanceByMenteeId(menteeId: String): Result<Attendance?> {
        val row = dataSource.getAttendanceByMenteeId(menteeId)
            .getOrElse { return Result.failure(it) }
            ?: return Result.success(null)
        val attendance=row.toDomain()
        return Result.success(attendance)
    }
}


