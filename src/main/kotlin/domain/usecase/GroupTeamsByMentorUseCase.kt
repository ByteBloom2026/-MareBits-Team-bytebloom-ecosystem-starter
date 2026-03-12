package domain.usecase
import data.repository.TeamRepository
import domain.model.Team
class GroupTeamsByMentorUseCase(private val teamRepository: TeamRepository){
    operator fun invoke(): Result<Map<String, List<Team>>> =
        Result.success(teamRepository.getAllTeams().groupBy {it.mentorLead})
    }
}