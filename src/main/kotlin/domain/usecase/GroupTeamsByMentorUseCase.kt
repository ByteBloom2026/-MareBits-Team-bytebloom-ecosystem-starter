package domain.usecase
import data.repository.TeamRepository
import domain.model.Team
class GroupTeamsByMentorUseCase(private val teamRepository: TeamRepository){
    operator fun invoke(): Result<Map<String, List<Team>>> =

        teamRepository.getAllTeams().fold(
            onSuccess = ::GroupTeamsByMentorSuccess,
            onFailure = ::GroupTeamsByMentoFailure

        )
}
private fun GroupTeamsByMentorSuccess(team: List<Team>): Result<Map<String, List<Team>>>{
    return Result.success(
        team.groupBy {it.mentorLead}
    )
}
private fun GroupTeamsByMentoFailure(error: Throwable):Result<Map<String, List<Team>>>{
    return Result.failure(error)
}