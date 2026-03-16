package domain.usecase
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.model.Team
import domain.usecase.request.GetMenteeNamesByTeamNameRequest
class GetMenteeNamesByTeamNameUseCase(
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
) {
    operator fun invoke(request: GetMenteeNamesByTeamNameRequest): Result<List<String>> {
        return teamRepository.searchTeamsByName(request.teamName).fold(
            onSuccess = ::GetMenteeNamesByTeamNameSuccess,
            onFailure = ::onGetMenteeNameByTeamNameFailure
        )
    }
    private fun GetMenteeNamesByTeamNameSuccess(teams: List<Team>): Result<List<String>> {
        val targetTeam = teams.firstOrNull()
        val allMentees = menteeRepository.getAllMentees().getOrDefault(emptyList())
        val names = allMentees
            .filter { it.teamId == targetTeam?.id }
            .map { it.name }
        return Result.success(names)
    }
    private fun onGetMenteeNameByTeamNameFailure(error: Throwable): Result<List<String>> {
        return Result.failure(error)
    }
}