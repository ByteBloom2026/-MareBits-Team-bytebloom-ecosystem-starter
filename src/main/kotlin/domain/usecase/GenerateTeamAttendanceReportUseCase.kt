package domain.usecase
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import domain.model.AttendanceState
import domain.usecase.request.*
class GenerateTeamAttendanceReportUseCase(
    private val attendanceRepository: AttendanceRepository,
    private val menteeRepository: MenteeRepository
) {
    operator fun invoke(teamId: requestTeamId): Map<String, Int> =
        getAbsenceCountPerMentee(teamId)

    private fun getAbsenceCountPerMentee(teamId: String): Map<String, Int> = menteeRepository.getMenteesByTeamId(teamId)
        .associate { mentee ->
            val absences = attendanceRepository
                .getAttendanceByMenteeId(mentee.id)
                ?.weeks
                ?.count {it == AttendanceState.PRESENT  }
                ?: 0
            mentee.id to absences
        }
}