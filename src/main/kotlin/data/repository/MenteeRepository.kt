package data.repository
import domain.model.Mentee
interface MenteeRepository {
    fun getAllMentees(): Result<List<Mentee>>
    fun getMenteeById(id: String): Result<Mentee?>
    fun getMenteesByTeamId(teamId: String): Result<List<Mentee>>
}