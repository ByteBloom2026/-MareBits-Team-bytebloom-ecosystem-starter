package domain.usecase
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import domain.model.AttendanceState
import domain.usecase.request.GenerateTeamAttendanceReportRequest
class GenerateTeamAttendanceReportUseCase(
    private val attendanceRepository: AttendanceRepository,
    private val menteeRepository: MenteeRepository
) {
    operator fun invoke(request: GenerateTeamAttendanceReportRequest): Result<Map<String, Int>> {
        return runCatching {
            getAbsenceCountPerMentee(request.teamId)
        }.fold(
            onSuccess = ::onGenerateTeamAttendanceReportSuccess,
            onFailure = ::onGenerateTeamAttendanceReportFailure
        )
    }
    private fun getAbsenceCountPerMentee(teamId: String): Map<String, Int> =
        menteeRepository.getMenteesByTeamId(teamId)
            .associate { mentee ->
                val absences = attendanceRepository
                    .getAttendanceByMenteeId(mentee.id)
                    ?.weeks
                    ?.count { it == AttendanceState.ABSENT }
                    ?: 0
                mentee.id to absences
            }
    private fun onGenerateTeamAttendanceReportSuccess(report: Map<String, Int>): Result<Map<String, Int>> {
        return Result.success(report)
    }
    private fun onGenerateTeamAttendanceReportFailure(error: Throwable): Result<Map<String, Int>> {
        return Result.failure(error)
    }
}