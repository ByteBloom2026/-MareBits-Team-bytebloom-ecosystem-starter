package domain.usecase
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.usecase.request.RequestTeamName

class GetMenteeNamesByTeamNameUseCase(
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
) {
    operator fun invoke(teamName: RequestTeamName): List<String> {
        val teamId = teamRepository.getTeamById(teamId = String())
            ?.id
            ?: return emptyList()
        return menteeRepository.getAllMentees()
            .filter { it.teamId == teamId }
            .map { it.name }
    }
}


