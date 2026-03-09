package domain.validation.validators
import domain.model.AttendanceState
import domain.model.exception.EmptyAttendanceWeeksException

import domain.validation.EcosystemValidator
class AttendanceWeeksValidator : EcosystemValidator<List<AttendanceState>> {
    override fun validate(attendanceStates: List<AttendanceState>):  List<AttendanceState> {
        if (attendanceStates.isEmpty())
          throw EmptyAttendanceWeeksException()
        return attendanceStates
    }
}