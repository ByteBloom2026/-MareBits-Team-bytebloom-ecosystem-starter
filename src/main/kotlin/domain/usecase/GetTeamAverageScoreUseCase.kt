package domain.usecase
import data.repository.MenteeRepository
import data.repository.PerformanceRepository
import domain.usecase.request.RequestTeamId

class GetTeamAverageScoreUseCase(
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(teamId: RequestTeamId): Double {
        val mentees = menteeRepository.getMenteesByTeamId(teamId)
        val allScores = mentees.flatMap { m ->
            performanceRepository.getPerformanceByMenteeId(m.id)
                .map { it.score}
        }
        return allScores.average().let { if (it.isNaN()) 0.0 else it }
    }
}