package domain.usecase
import data.repository.TeamRepository
import domain.model.Team
class FindTeamswithNoProjectUseCase(private val teamRepository: TeamRepository) {
    operator fun invoke(): Result<List<Team>> {
        return Result.success(
            teamRepository.getAllTeams().filter { it.projects == null })
    }
}