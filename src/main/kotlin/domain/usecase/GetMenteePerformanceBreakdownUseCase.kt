package domain.usecase
import data.repository.PerformanceRepository
import domain.model.PerformanceSubmission.SubmissionType

class  GetMenteePerformanceBreakdownUseCase(
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(menteeId: String): Map<SubmissionType, Double> {
        val submissions = performanceRepository.getPerformanceByMenteeId(menteeId)
        return submissions.groupBy { it.type }
            .mapValues { (_, list) ->
                val avg = list.map { it.score }.average()
                if (avg.isNaN()) 0.0 else avg
            }
    }
}