package domain.usecase
import data.repository.TeamRepository
import domain.model.Team
import domain.usecase.request.SearchTeamsByNameRequest

class SearchTeamsByNameUseCase(
    private val teamRepository: TeamRepository
) {
    operator fun invoke(request: SearchTeamsByNameRequest): Result<List<Team>> {
        return teamRepository.getAllTeams().fold(
            onSuccess = { teams -> onSearchTeamsByNameSuccess(teams, request.keyword) },
            onFailure = ::onSearchTeamsByNameFailure
        )
    }
    private fun onSearchTeamsByNameSuccess(teams: List<Team>, keyword: String): Result<List<Team>> {
        val filteredTeams = teams.filter { it.name.contains(keyword, ignoreCase = true) }
        return Result.success(filteredTeams)
    }
    private fun onSearchTeamsByNameFailure(error: Throwable): Result<List<Team>> {
        return Result.failure(error)
    }
}