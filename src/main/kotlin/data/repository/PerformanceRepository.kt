package data.repository
import domain.model.PerformanceSubmission
interface PerformanceRepository {
    fun getAllPerformance(): Result<List<PerformanceSubmission>>
    fun getPerformanceByMenteeId(menteeId: String): Result<List<PerformanceSubmission>>
}