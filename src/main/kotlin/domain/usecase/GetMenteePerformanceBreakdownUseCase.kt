package domain.usecase
import data.repository.PerformanceRepository
import domain.model.PerformanceSubmission
import domain.model.PerformanceSubmission.SubmissionType
import domain.usecase.request.GetMenteePerformanceBreakdownRequest

class  GetMenteePerformanceBreakdownUseCase(
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(request: GetMenteePerformanceBreakdownRequest): Result<Map<SubmissionType, Double>> {
        return performanceRepository.getPerformanceByMenteeId(request.menteeId)
            .fold(
            onSuccess = ::onGetMenteePerformanceBreakdownSuccess,
            onFailure = ::onGetMenteePerformanceBreakdownFailure
        )
    }
    private fun onGetMenteePerformanceBreakdownSuccess(submissions: List<PerformanceSubmission>): Result<Map<SubmissionType, Double>> {
        val breakdown = submissions.groupBy { it.type }
            .mapValues { (_, list) ->
                val avg = list.map { it.score }.average()
                if (avg.isNaN()) 0.0 else avg
            }
        return Result.success(breakdown)
    }
    private fun onGetMenteePerformanceBreakdownFailure(error: Throwable): Result<Map<SubmissionType, Double>> {
        return Result.failure(error)
    }
}