package data.repository
import domain.model.Attendance
interface AttendanceRepository {
    fun getAllAttendance(): Result<List<Attendance>>
    fun getAttendanceByMenteeId(menteeId: String): Result<Attendance?>

}