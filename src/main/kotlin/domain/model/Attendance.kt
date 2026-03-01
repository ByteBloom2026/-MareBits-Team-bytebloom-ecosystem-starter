package domain.model
import domain.validation.ValidationResult
import domain.validation.validators.MenteeIdValidator
data class Attendance private constructor(
    val menteeId: String,
    val weeks: List<AttendanceState>
    ){
    companion object {
        private val idValidator = MenteeIdValidator()

        fun create(menteeId: String, weeks: List<AttendanceState>): ValidationResult<Attendance> {
            val idResult = idValidator.validate(menteeId)
            if (idResult is ValidationResult.Failure) return idResult
            if (weeks.isEmpty()) {
                return ValidationResult.failure("weeks", "Attendance must contain at least one week.")
            }
            val cleanId = (idResult as ValidationResult.Success).data
            return ValidationResult.success(
                Attendance(
                    menteeId = cleanId,
                    weeks = weeks
                )
            )
        }
    }
}