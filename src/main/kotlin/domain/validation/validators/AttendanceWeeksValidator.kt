package domain.validation.validators
import domain.model.AttendanceState
import domain.model.exception.AttendanceException
import domain.validation.EcosystemValidator

class AttendanceWeeksValidator : EcosystemValidator<List<AttendanceState>> {
    override fun validate(attendanceStates: List<AttendanceState>):  List<AttendanceState> {
        if (attendanceStates.isEmpty())
            throw AttendanceException.EmptyAttendanceWeeksException()
        return attendanceStates
    }
}