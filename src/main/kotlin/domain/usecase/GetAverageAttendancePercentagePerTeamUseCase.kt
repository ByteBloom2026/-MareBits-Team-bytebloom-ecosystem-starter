package domain.usecase
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.model.AttendanceState
class GetAverageAttendancePercentagePerTeamUseCase(
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
    private val attendanceRepository: AttendanceRepository
) {
    operator fun invoke(): Result<Map<String, Double>> {
        val averages = teamRepository.getAllTeams().associate { team ->
            team.name to calculateAverageAttendance(team.id)
        }
        return Result.success(averages)
    }
    private fun calculateAverageAttendance(teamId: String): Double {
        val mentees = menteeRepository.getMenteesByTeamId(teamId)

        val percentages = mentees.mapNotNull { mentee ->
            calculateMenteeAttendancePercentage(mentee.id)
        }
        return percentages.average().takeIf { !it.isNaN() } ?: 0.0
    }
    private fun calculateMenteeAttendancePercentage(menteeId: String): Double? {
        val weeks = attendanceRepository.getAttendanceByMenteeId(menteeId)?.weeks ?: return null
        val presentCount = weeks.count { it == AttendanceState.PRESENT }
        return (presentCount.toDouble() / weeks.size) * 100
    }
}