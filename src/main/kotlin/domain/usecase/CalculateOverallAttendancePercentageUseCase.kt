package domain.usecase
import data.repository.AttendanceRepository
import domain.model.Attendance
import domain.model.AttendanceState
import domain.usecase.request.CalculateOverallAttendancePercentageRequest
class CalculateOverallAttendancePercentageUseCase (
    private val attendanceRepository: AttendanceRepository
) {
    operator fun invoke(request: CalculateOverallAttendancePercentageRequest): Result<Double> {
        return attendanceRepository.getAttendanceByMenteeId(request.menteeId).fold(
            onSuccess = ::onCalculateAttendanceSuccess,
            onFailure = ::onCalculateAttendanceFailure
        )
    }
    private fun onCalculateAttendanceSuccess(attendance: Attendance?): Result<Double> {
        if (attendance == null || attendance.weeks.isEmpty()) {
            return Result.success(0.0)
        }
        val percentage = calculatePercentage(attendance.weeks)
        return Result.success(percentage)
    }
    private fun onCalculateAttendanceFailure(error: Throwable): Result<Double> {
        return Result.failure(error)
    }
    private fun calculatePercentage(weeks: List<AttendanceState>): Double {
        val totalPoints = weeks.sumOf(::mapAttendanceStateToPoint)
        val percentage = (totalPoints / weeks.size) * 100
        return String.format("%.2f", percentage).toDouble()
    }
    private fun mapAttendanceStateToPoint(state: AttendanceState): Double {
        return when (state) {
            AttendanceState.PRESENT -> 1.0
            AttendanceState.LATE -> 0.0
            AttendanceState.ABSENT -> 0.0
        }
    }
}