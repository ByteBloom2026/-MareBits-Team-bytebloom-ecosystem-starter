package domain.usecase
import data.repository.MenteeRepository
import data.repository.PerformanceRepository
import domain.model.Mentee
import domain.model.PerformanceSubmission
import domain.usecase.request.GetTopPerformingMenteesBySubmissionTypeRequest
class GetTopPerformingMenteesBySubmissionTypeUseCase (
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(
        request: GetTopPerformingMenteesBySubmissionTypeRequest
    ): Result<List<Mentee>> {
        return performanceRepository.getAllPerformance().fold(
            onSuccess = { performances ->
                onGetTopPerformingMenteesBySubmissionTypeSuccess(
                    performances,
                    request.submissionType
                )
            },
            onFailure = ::onGetTopPerformingMenteesBySubmissionTypeFailure
        )
    }
    private fun onGetTopPerformingMenteesBySubmissionTypeSuccess(
        performances: List<PerformanceSubmission>,
        submissionType: PerformanceSubmission.SubmissionType
    ): Result<List<Mentee>> {
        val filteredPerformances = performances.filter { performance ->
            performance.type == submissionType
        }
        if (filteredPerformances.isEmpty()) {
            return Result.success(emptyList())
        }
        val averageScores = filteredPerformances
            .groupBy { performance -> performance.menteeId }
            .mapValues { (_, submissions) ->
                submissions.map { submission -> submission.score }.average()
            }
        val highestAverageScore = averageScores.maxOfOrNull { (_, averageScore) ->
            averageScore
        } ?: return Result.success(emptyList())

        val topMentees = averageScores
            .filterValues { averageScore -> averageScore == highestAverageScore }
            .keys
            .mapNotNull { menteeId ->
                menteeRepository.getMenteeById(menteeId).getOrNull()
            }
        return Result.success(topMentees)
    }
    private fun onGetTopPerformingMenteesBySubmissionTypeFailure(
        error: Throwable
    ): Result<List<Mentee>> {
        return Result.failure(error)
    }
}