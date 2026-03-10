package domain.usecase

import domain.usecase.request.RequestTeamId
import data.repository.MenteeRepository
import data.repository.PerformanceRepository

class GetTeamAverageScoreUseCase(
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(request: RequestTeamId): Double {
        val mentees = menteeRepository.getMenteesByTeamId(request.teamId)
        val allScores = mentees.flatMap { m ->
            performanceRepository.getPerformanceByMenteeId(m.id).map { it.score }
        }
        return if (allScores.isNotEmpty()) allScores.average() else 0.0
    }
}