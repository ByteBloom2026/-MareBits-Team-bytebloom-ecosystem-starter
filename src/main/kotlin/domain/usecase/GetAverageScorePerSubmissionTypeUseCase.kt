package domain.usecase
import data.repository.PerformanceRepository
import domain.model.PerformanceSubmission
class GetAverageScorePerSubmissionTypeUseCase(
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(): Result<Map<PerformanceSubmission.SubmissionType, Double>> {
        val allPerformances = performanceRepository.getAllPerformance()
        return Result.success(
            allPerformances.groupBy { it.type }
            .mapValues { (_, submissions) ->
                submissions.map { it.score }.average().let { if (it.isNaN()) 0.0 else it }
            }
        )
    }
}