package domain.usecase
import data.repository.MenteeRepository
import data.repository.PerformanceRepository
import domain.model.Mentee

class GetTopScoringMenteeUseCase(
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(): Result<Mentee?> =
         menteeRepository.getAllMentees().fold(
             onSuccess = { mentees -> GetTopScoringMenteeSuccess(mentees, performanceRepository) },
             onFailure = ::GetTopScoringMenteeFailure
         )
}
private fun GetTopScoringMenteeSuccess(mentees: List<Mentee>,performanceRepository: PerformanceRepository): Result<Mentee?> {
    val topMentee = mentees.maxByOrNull { mentee ->
        val scores = performanceRepository.getPerformanceByMenteeId(mentee.id)
            .getOrDefault(emptyList())
            .map { it.score }
        scores.average()
            .let { if (it.isNaN()) 0.0 else it }
    }
    return Result.success(topMentee)
}
private fun GetTopScoringMenteeFailure(error: Throwable): Result<Mentee?>{
    return Result.failure(error)
}//


