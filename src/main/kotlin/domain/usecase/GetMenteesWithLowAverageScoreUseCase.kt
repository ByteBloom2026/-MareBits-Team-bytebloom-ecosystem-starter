package domain.usecase
import data.repository.MenteeRepository
import data.repository.PerformanceRepository
import domain.model.Mentee
import domain.usecase.request.*
class GetMenteesWithLowAverageScoreUseCase(
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(threshold: Requestthreshold): List<Mentee> {
        return menteeRepository.getAllMentees()
            .filter { mentee ->
                val avgScore = performanceRepository.getPerformanceByMenteeId(mentee.id)
                    .map { it.score}
                    .average().let { if (it.isNaN()) 0.0 else it }


                avgScore < threshold
            }
    }

}