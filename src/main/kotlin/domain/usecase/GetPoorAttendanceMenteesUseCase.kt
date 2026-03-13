package domain.usecase
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import domain.model.Mentee
import domain.model.AttendanceState
import domain.usecase.request.GetPoorAttendanceMenteesRequest
class GetPoorAttendanceMenteesUseCase(
    private val attendanceRepository: AttendanceRepository,
    private val menteeRepository: MenteeRepository
) {
    operator fun invoke(request: GetPoorAttendanceMenteesRequest): Result<List<Mentee>> {
        return menteeRepository.getAllMentees()
            .fold(
            onSuccess = { mentees -> onGetPoorAttendanceSuccess(mentees, request.minAbsences) },
            onFailure = ::onGetPoorAttendanceFailure
        )
    }
    private fun onGetPoorAttendanceSuccess(mentees: List<Mentee>, minAbsences: Int): Result<List<Mentee>> {
        val poorAttendanceMentees = mentees.filter { mentee ->
            attendanceRepository.getAttendanceByMenteeId(mentee.id).getOrNull()
                ?.weeks
                ?.count { it != AttendanceState.PRESENT }
                ?.let { it >= minAbsences }
                ?: false
        }
        return Result.success(poorAttendanceMentees)
    }
    private fun onGetPoorAttendanceFailure(error: Throwable): Result<List<Mentee>> {
        return Result.failure(error)
    }
}