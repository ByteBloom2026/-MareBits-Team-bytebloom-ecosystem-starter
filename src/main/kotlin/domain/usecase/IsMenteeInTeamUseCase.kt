package domain.usecase
import domain.model.Mentee
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.usecase.request.RequestMenteeId
import domain.usecase.request.RequestTeamName

class IsMenteeInTeamUseCase(
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
) {
    operator fun invoke(menteeId:RequestMenteeId,teamName:RequestTeamName): Boolean{
        val mentee = menteeRepository.getAllMentees()
            .find { it.id == menteeId }
            ?: return false
        return ismenteeInTheTeam(mentee, teamName)
    }

    private fun ismenteeInTheTeam(mentee: Mentee, teamName: String): Boolean {
        return teamRepository.getAllTeams()
            .find { it.id == mentee.teamId }
            ?.name == teamName
    }
}