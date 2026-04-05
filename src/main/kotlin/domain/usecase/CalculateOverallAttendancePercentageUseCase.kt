package domain.usecase
import data.repository.AttendanceRepository
import domain.usecase.request.CalculateOverallAttendancePercentageRequest
class CalculateOverallAttendancePercentageUseCase (
    private val attendanceRepository: AttendanceRepository
) {
    operator fun invoke(request: CalculateOverallAttendancePercentageRequest): Result<Double> {
        return Result.success(0.0)
    }
}