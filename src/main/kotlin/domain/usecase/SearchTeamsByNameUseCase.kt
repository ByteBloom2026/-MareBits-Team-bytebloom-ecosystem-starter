package domain.usecase
import domain.usecase.request.*
import data.repository.TeamRepository
import domain.model.Team

class SearchTeamsByNameUseCase(
    private val teamRepository: TeamRepository
) {
    operator fun invoke(keyword: Requestkeyword): Result<List<Team>> {
        return teamRepository.getAllTeams()
    }
}


