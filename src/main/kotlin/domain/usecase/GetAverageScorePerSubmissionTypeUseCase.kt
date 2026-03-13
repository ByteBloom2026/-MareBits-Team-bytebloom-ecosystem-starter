package domain.usecase
import data.repository.PerformanceRepository
import domain.model.PerformanceSubmission
import domain.model.PerformanceSubmission.SubmissionType

class GetAverageScorePerSubmissionTypeUseCase(
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(): Result<Map<SubmissionType, Double>> {
        return performanceRepository.getAllPerformance().fold(
            onSuccess = ::GetAverageScorePerSubmissionTypeSuccess,
            onFailure = ::GetAverageScorePerSubmissionTypeFailure
        )
    }
    private fun GetAverageScorePerSubmissionTypeSuccess(allPerformances: List<PerformanceSubmission>): Result<Map<SubmissionType, Double>> {
        val averages = allPerformances.groupBy { it.type }
            .mapValues { (_, submissions) ->
                val avg = submissions.map { it.score }.average()
                if (avg.isNaN()) 0.0 else avg
            }
        return Result.success(averages)
    }
    private fun GetAverageScorePerSubmissionTypeFailure(error: Throwable): Result<Map<SubmissionType, Double>> {
        return Result.failure(error)
    }
}