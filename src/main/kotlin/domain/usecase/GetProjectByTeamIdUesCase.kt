package domain.usecase

import data.repository.TeamRepository
import domain.model.Project
import domain.usecase.request.RequestTeamId

class GetProjectByTeamIdUesCase(private val teamRepository: TeamRepository) {
    operator fun invoke(teamId: RequestTeamId): Project? {
        return teamRepository.getTeamById(teamId)?.projects

    }
}