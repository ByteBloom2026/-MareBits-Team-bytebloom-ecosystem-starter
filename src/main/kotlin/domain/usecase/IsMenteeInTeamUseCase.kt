package domain.usecase
import domain.model.Mentee
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.usecase.request.IsMenteeInTeamRequest
class IsMenteeInTeamUseCase(
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
) {
    operator fun invoke(request: IsMenteeInTeamRequest): Result<Boolean> {
        return menteeRepository.getAllMentees().fold(
            onSuccess = { mentees -> onIsMenteeInTeamSuccess(mentees, request.menteeId, request.teamName) },
            onFailure = ::onIsMenteeInTeamFailure
        )
    }
    private fun onIsMenteeInTeamSuccess(mentees: List<Mentee>, menteeId: String, teamName: String): Result<Boolean> {
        val mentee = mentees.find { it.id == menteeId } ?: return Result.success(false)
        val team = teamRepository.getAllTeams()
            .getOrNull()
            ?.find { it.id == mentee.teamId }
        return Result.success(team?.name == teamName)
    }
    private fun onIsMenteeInTeamFailure(error: Throwable): Result<Boolean> {
        return Result.failure(error)
    }
}