package domain.usecase
import data.repository.MenteeRepository
import data.repository.PerformanceRepository
import domain.model.Mentee
import domain.usecase.request.GetMenteesWithLowAverageScoreRequest
class GetMenteesWithLowAverageScoreUseCase(
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(request: GetMenteesWithLowAverageScoreRequest): Result<List<Mentee>> {
        return menteeRepository.getAllMentees().fold(
            onSuccess = { mentees -> onMenteeWithLowAverageSuccess(mentees, request.threshold) },
            onFailure = ::onMenteeWithLowAverageFailure
        )
    }
    private fun onMenteeWithLowAverageSuccess(mentees: List<Mentee>, threshold: Double): Result<List<Mentee>> {
        val lowScoringMentees = mentees.filter { mentee ->
            val avgScore = performanceRepository.getPerformanceByMenteeId(mentee.id)
                .map { it.score }
                .average().let { if (it.isNaN()) 0.0 else it }

            avgScore < threshold
        }
        return Result.success(lowScoringMentees)
    }
    private fun onMenteeWithLowAverageFailure(error: Throwable): Result<List<Mentee>> {
        return Result.failure(error)
    }
}