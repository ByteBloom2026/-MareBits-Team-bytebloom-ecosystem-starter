package data.repository
import domain.model.Team
interface TeamRepository {
    fun getAllTeams(): Result<List<Team>>
    fun getTeamById(teamId: String): Result<Team?>
    fun getMentorLeadByTeamId(teamId: String): Result<String?>
}