package data.repository
import domain.model.Team
interface TeamRepository {
    suspend fun getAllTeams(): Result<List<Team>>
    suspend fun getTeamById(teamId: String): Result<Team?>
    suspend fun getMentorLeadByTeamId(teamId: String): Result<String?>
    suspend fun searchTeamsByName(keyword: String): Result<List<Team>>
}