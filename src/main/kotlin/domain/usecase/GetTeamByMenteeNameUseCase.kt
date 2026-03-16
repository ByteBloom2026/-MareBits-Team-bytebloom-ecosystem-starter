package domain.usecase
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.model.Mentee
import domain.usecase.request.GetTeamByMenteeNameRequest

class GetTeamByMenteeNameUseCase (
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
) {
    operator fun invoke(request: GetTeamByMenteeNameRequest): Result<String?> {
        return menteeRepository.getAllMentees().fold(
            onSuccess = { mentees -> onGetTeamByMenteeNameSuccess(mentees, request.menteeName) },
            onFailure = ::onGetTeamByMenteeNameFailure
        )
    }

    private fun onGetTeamByMenteeNameSuccess(mentees: List<Mentee>, menteeName: String): Result<String?> {
        val mentee = mentees.find { it.name == menteeName }
        val team = teamRepository.getAllTeams()
            .getOrNull()
            ?.find { it.id == mentee?.teamId }
        return Result.success(team?.name)
    }

    private fun onGetTeamByMenteeNameFailure(error: Throwable): Result<String?> {
        return Result.failure(error)
    }
}


