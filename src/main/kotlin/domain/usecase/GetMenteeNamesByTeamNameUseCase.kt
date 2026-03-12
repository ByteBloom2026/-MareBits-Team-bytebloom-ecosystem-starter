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
            onSuccess = ::onGetMenteeNameByTeamNameSucess,
            onFailure = ::onGetMenteeNameByTeamNameFailure
        )
    }
    private fun onGetMenteeNameByTeamNameSucess(team: Team?): Result<List<String>> {
        val names = menteeRepository.getAllMentees()
            .filter { it.teamId == team?.id }
            .map { it.name }
        return Result.success(names)
    }
    private fun onGetMenteeNameByTeamNameFailure(error: Throwable): Result<List<String>> {
        return Result.failure(error)
    }
}