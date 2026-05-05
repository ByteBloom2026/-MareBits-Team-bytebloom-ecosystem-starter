package data.repository
import domain.model.PerformanceSubmission
interface PerformanceRepository {
    suspend fun getAllPerformance(): Result<List<PerformanceSubmission>>
    suspend fun getPerformanceByMenteeId(menteeId: String): Result<List<PerformanceSubmission>>
    suspend fun getPerformanceByTeamId(teamId:String) : Result<List<PerformanceSubmission>>
}