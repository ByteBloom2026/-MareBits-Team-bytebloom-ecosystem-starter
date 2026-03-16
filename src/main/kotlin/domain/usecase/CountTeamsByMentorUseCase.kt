package domain.usecase
import data.repository.TeamRepository
import domain.model.Team

class CountTeamsByMentorUseCase(private val teamRepository: TeamRepository) {

    operator fun invoke(): Result<Map<String, Int>> {
        return teamRepository.getAllTeams().fold(
            onSuccess = ::onCountTeamsSuccess,
            onFailure = { Result.failure(it) }
        )
    }
    private fun onCountTeamsSuccess(teams: List<Team>): Result<Map<String, Int>> {
        val countMap = teams.groupBy { it.mentorLead ?: "No Mentor" }
            .mapValues { it.value.size }
        return Result.success(countMap)
    }
}