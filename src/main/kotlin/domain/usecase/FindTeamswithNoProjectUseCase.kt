package domain.usecase
import data.repository.TeamRepository
import domain.model.Team
class FindTeamsWithNoProjectUseCase(private val teamRepository: TeamRepository) {
    operator fun invoke(): Result<List<Team>> {
        return teamRepository.getAllTeams()
            .fold(
            onSuccess = ::onFindTeamsSuccess,
            onFailure = { Result.failure(it) }
        )
    }
    private fun onFindTeamsSuccess(teams: List<Team>): Result<List<Team>> {
        val filteredTeams = teams.filter { it.projects == null }
        return Result.success(filteredTeams)
    }
}