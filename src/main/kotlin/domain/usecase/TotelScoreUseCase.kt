package domain.usecase

import data.repository.PerformanceRepository
import data.repository.TeamRepository

class TotalScore(
    private val performanceRepo: PerformanceRepository,
    private val teamRepository: TeamRepository
) {
    operator fun invoke(): Result<Double> {
        return performanceRepo.getAllPerformance()
            .fold(
            onSuccess =  {onCalculateTotalScoreOnSuccess()},
            onFailure = ::onCalculateTotalScoreOnFailure
        )
    }
    private fun calculateTotalScore(): Double {
        val teamScoresList = teamRepository.getAllTeams().getOrThrow()
        val finalResult = teamScoresList.map { teams ->
            val preformanceResalt = performanceRepo
                .getPerformanceByTeamId(teams.id).getOrNull()
            val totelScore = preformanceResalt
                ?.sumOf { it.score }
                ?:0.0
            totelScore
        }.sum()

        return finalResult
    }

    private fun onCalculateTotalScoreOnSuccess(): Result<Double> {
        return Result.success(calculateTotalScore())
    }

    private fun onCalculateTotalScoreOnFailure(error: Throwable): Result<Double> {
        return Result.failure(error)
    }
}
