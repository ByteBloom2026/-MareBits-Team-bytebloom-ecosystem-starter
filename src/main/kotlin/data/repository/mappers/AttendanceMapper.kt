package data.repository.mappers
import data.datasource.model.AttendanceRow
import domain.model.Attendance
import domain.model.AttendanceState
import domain.validation.ValidationResult
fun AttendanceRow.toDomain(): ValidationResult<Attendance> {
    val states = weeks.split(",").map { token ->
        when (token.trim().uppercase()) {
            "PRESENT" -> AttendanceState.PRESENT
            "LATE" -> AttendanceState.LATE
            "ABSENT" -> AttendanceState.ABSENT
            else -> AttendanceState.ABSENT
        }
    }
    return Attendance.create(menteeId = menteeId, weeks = states)
}

