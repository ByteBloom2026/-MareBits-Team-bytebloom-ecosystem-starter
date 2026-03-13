package domain.usecase
import data.repository.MenteeRepository
import data.repository.PerformanceRepository
import domain.model.Mentee
import domain.usecase.request.GetTeamAverageScoreRequest
class GetTeamAverageScoreUseCase(
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(request: GetTeamAverageScoreRequest): Result<Double> {
        return menteeRepository.getMenteesByTeamId(request.teamId)
            .fold(
        onSuccess = { mentees -> onGetTeamAverageScoreSuccess(mentees) },
        onFailure = ::onGetTeamAverageScoreFailure
        )
    }
    private fun onGetTeamAverageScoreSuccess(mentees: List<Mentee>): Result<Double>{
        val allScores = mentees.flatMap { mentee ->
           val performance= performanceRepository.getPerformanceByMenteeId(mentee.id).getOrNull()
            performance?.map { it.score} ?:emptyList()
        }
        val average = allScores.average().let { if (it.isNaN()) 0.0 else it }
        return Result.success(average)
    }
    private fun onGetTeamAverageScoreFailure(error: Throwable): Result<Double>{
        return Result.failure(error)

    }
}