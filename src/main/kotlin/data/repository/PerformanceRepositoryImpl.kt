package data.repository
import domain.model.PerformanceSubmission
import data.EcoSystemDataSource
import data.repository.mappers.toDomain

class PerformanceRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : PerformanceRepository {
    override fun getAllPerformance(): Result<List<PerformanceSubmission>> {
        val rows = dataSource.getPerformances()
            .getOrElse { return Result.failure(it) }
            .map { it.toDomain() }
        return Result.success(rows)
    }
    override fun getPerformanceByMenteeId(menteeId: String): Result<List<PerformanceSubmission>> {
        val rows = dataSource.getPerformanceByMenteeId(menteeId)
            .getOrElse { return Result.failure(it) }
            .map { it.toDomain() }
        return Result.success(rows)
    }
}
