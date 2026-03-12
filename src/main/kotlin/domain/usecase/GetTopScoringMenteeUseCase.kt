package domain.usecase
import data.repository.MenteeRepository
import data.repository.PerformanceRepository
import domain.model.Mentee
class GetTopScoringMenteeUseCase(
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(): Result<Mentee?> =
         Result.success(menteeRepository.getAllMentees().maxByOrNull { m ->
            val scores = performanceRepository.getPerformanceByMenteeId(m.id)
                .map { it.score}
            scores.average().let { if (it.isNaN()) 0.0 else it }
        }
         )

}