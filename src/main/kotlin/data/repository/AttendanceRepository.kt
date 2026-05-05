package data.repository
import domain.model.Attendance
interface AttendanceRepository {
    suspend fun getAllAttendance(): Result<List<Attendance>>
    suspend fun getAttendanceByMenteeId(menteeId: String): Result<Attendance?>

}