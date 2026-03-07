package domain.usecase
import data.repository.PerformanceRepository
import domain.model.PerformanceSubmission

class GetAverageScorePerSubmissionTypeUseCase(
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(): Map<PerformanceSubmission.SubmissionType, Double> {
        val allPerformances = performanceRepository.getAllPerformance()

        return allPerformances
            .groupBy { it.type }
            .mapValues { (_, submissions) ->
                submissions.map { it.score }.average().let { if (it.isNaN()) 0.0 else it }
            }
    }
}