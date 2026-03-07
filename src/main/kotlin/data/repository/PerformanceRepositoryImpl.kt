package data.repository
import domain.model.PerformanceSubmission
import data.EcoSystemDataSource
import data.repository.mappers.toDomain
import domain.validation.ValidationResult
class PerformanceRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : PerformanceRepository {
    override fun getAllPerformance(): Result<List<PerformanceSubmission>> {
        val rows = dataSource.getPerformances().getOrElse { return Result.failure(it) }
        val out = mutableListOf<PerformanceSubmission>()
        for (row in rows) {
            when (val mapped = row.toDomain()) {
                is ValidationResult.Success -> out.add(mapped.data)
                is ValidationResult.Failure -> { }
            }
        }
        return Result.success(out)
    }
    override fun getPerformanceByMenteeId(menteeId: String): Result<List<PerformanceSubmission>> {
        val rows = dataSource.getPerformanceByMenteeId(menteeId).getOrElse { return Result.failure(it) }
        val out = mutableListOf<PerformanceSubmission>()
        for (row in rows) {
            when (val mapped = row.toDomain()) {
                is ValidationResult.Success -> out.add(mapped.data)
                is ValidationResult.Failure -> { }
            }
        }
        return Result.success(out)
    }
}
