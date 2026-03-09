package domain.usecase

import data.repository.TeamRepository
import domain.model.Team

class SearchTeamsByNameUseCase(
    private val teamRepository: TeamRepository
) {
    operator fun invoke(keyword: String): Result<List<Team>> {
        return teamRepository.getAllTeams()
    }
}


