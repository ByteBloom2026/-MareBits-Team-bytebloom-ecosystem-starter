package domain.usecase
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.usecase.request.RequestMenteeNameId

class GetTeamByMenteeNameUseCase (
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
){
    operator fun invoke(menteeName: RequestMenteeNameId): String? {
        val mentee = menteeRepository.getAllMentees()
            .find { it.name == menteeName }
        val team = teamRepository.getAllTeams()
            .find { it.id == mentee?.teamId }
        return team?.name
    }
}