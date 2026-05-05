package data.repository
import domain.model.Mentee
interface MenteeRepository {
    suspend fun getAllMentees(): Result<List<Mentee>>
    suspend fun getMenteeById(id: String): Result<Mentee?>
    suspend fun getMenteesByTeamId(teamId: String): Result<List<Mentee>>
}