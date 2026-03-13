package domain.usecase
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.model.AttendanceState
import domain.model.Team

class GetAverageAttendancePercentagePerTeamUseCase(
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
    private val attendanceRepository: AttendanceRepository
) {
    operator fun invoke(): Result<Map<String, Double>> {
        return teamRepository.getAllTeams().fold(
            onSuccess = ::GetAverageAttendancePercentagePerTeamSuccess,
            onFailure = ::GetAverageAttendancePercentagePerTeamFailure
        )
    }
    private fun GetAverageAttendancePercentagePerTeamSuccess(teams: List<Team>): Result<Map<String, Double>> {
        val teamAverages = teams.associate { team ->
            team.name to calculateAverageAttendance(team.id)
        }
        return Result.success(teamAverages)
    }
    private fun GetAverageAttendancePercentagePerTeamFailure(error: Throwable): Result<Map<String, Double>> {
        return Result.failure(error)
    }
    private fun calculateAverageAttendance(teamId: String): Double {
        val mentees = menteeRepository.getMenteesByTeamId(teamId).getOrDefault(emptyList())

        if (mentees.isEmpty()) return 0.0

        val percentages = mentees.mapNotNull { mentee ->
            calculateMenteeAttendancePercentage(mentee.id)
        }

        val avg = percentages.average()
        return if (avg.isNaN()) 0.0 else avg
    }
    private fun calculateMenteeAttendancePercentage(menteeId: String): Double? {
        val attendance = attendanceRepository.getAttendanceByMenteeId(menteeId).getOrNull()
        val weeks = attendance?.weeks ?: return null

        if (weeks.isEmpty()) return 0.0

        val presentCount = weeks.count { it == AttendanceState.PRESENT }
        return (presentCount.toDouble() / weeks.size) * 100
    }
}