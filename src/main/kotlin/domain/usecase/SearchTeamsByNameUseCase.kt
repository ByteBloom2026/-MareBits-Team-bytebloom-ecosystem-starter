package domain.usecase

import domain.usecase.request.RequestTeamName
import data.repository.TeamRepository
import domain.model.Team

class SearchTeamsByNameUseCase(
    private val teamRepository: TeamRepository
) {
    operator fun invoke(request: RequestTeamName): List<Team> {
        val keyword = request.teamName
        return teamRepository.getAllTeams().filter {
            it.name.contains(keyword, ignoreCase = true)
        }
    }
}