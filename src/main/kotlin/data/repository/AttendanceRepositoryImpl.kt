package data.repository
import data.EcoSystemDataSource
import domain.model.Attendance
import data.repository.mappers.toDomain
import domain.validation.ValidationResult

class AttendanceRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : AttendanceRepository {
    override fun getAllAttendance(): Result<List<Attendance>> {
        val rows = dataSource.getAttendances().getOrElse {
            return Result.failure(it) }
        val out = mutableListOf<Attendance>()
        for (row in rows) {
            when (val mapped = row.toDomain()) {
                is ValidationResult.Success -> out.add(mapped.data)
                is ValidationResult.Failure -> {
                    print(" Handle or log the error here if needed")}
            }
        }
        return Result.success(out)
    }
    override fun getAttendanceByMenteeId(menteeId: String): Result<Attendance?> {
        val row = dataSource.getAttendanceByMenteeId(menteeId).getOrElse {
               return Result.failure(it) }
            ?: return Result.success(null)
        return when (val mapped = row.toDomain()) {
            is ValidationResult.Success -> Result.success(mapped.data)
            is ValidationResult.Failure -> Result.success(null)
        }
    }
}


