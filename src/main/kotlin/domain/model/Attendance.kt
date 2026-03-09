package domain.model
import domain.validation.validators.MenteeIdValidator
import domain.validation.validators.AttendanceWeeksValidator
data class Attendance private constructor(
    val menteeId: String,
    val weeks: List<AttendanceState>
    ){
    companion object {
        private val idValidator = MenteeIdValidator()
        val attendanceWeeksValidator = AttendanceWeeksValidator()
        fun create(menteeId: String, weeks: List<AttendanceState>): Attendance{
            val validMenteeId = idValidator.validate(menteeId)
            val validWeeks = attendanceWeeksValidator.validate(weeks)
            return Attendance(
                    menteeId = validMenteeId,
                    weeks = validWeeks
            )
        }
    }
}