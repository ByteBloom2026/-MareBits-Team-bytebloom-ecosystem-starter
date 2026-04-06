package domain.usecase
import domain.model.CrossTeamPerformanceReport
import data.repository.PerformanceRepository
import data.repository.TeamRepository
import domain.usecase.request.GenerateTeamAttendanceReportRequest
import domain.model.TeamScore


class GenerateCrossTeamPreformanceReportUseCase(
    private val teamRepository: TeamRepository,
    private val preformancerepo: PerformanceRepository
) {
    operator fun invoke(request: GenerateTeamAttendanceReportRequest): Result<CrossTeamPerformanceReport> {
            return runCatching {
                getReportByPreformance(request.teamId)
            }
            .fold(
                onSuccess = ::GenerateCrossTeamPreformanceReportUseCaseonSuccess,
                onFailure = ::GenerateCrossTeamPreformanceReportUseCaseonFailure
            )
    }
    private fun getReportByPreformance(teamId: String): CrossTeamPerformanceReport{
        val teamScoresList = teamRepository.getAllTeams()
            .getOrThrow()
            .map { teams ->
            val preformanceResalt = preformancerepo
                .getPerformanceByTeamId(teams.id).getOrNull()
            val TotelScore = preformanceResalt
                ?.sumOf { it.score }
                ?: 0.0
            TeamScore(
                Score = TotelScore,
                TeamName = teams.name
            )
        }
        val topPerformance = teamScoresList.maxByOrNull { it.Score }
            ?.takeIf { it.Score > 0 }

        return CrossTeamPerformanceReport(
            ScoreAndTeamName = teamScoresList,
            Repotr = topPerformance
        )

    }

    private fun GenerateCrossTeamPreformanceReportUseCaseonSuccess (report: CrossTeamPerformanceReport): Result<CrossTeamPerformanceReport>{
         return Result.success(
             value = report)
    }
    private fun GenerateCrossTeamPreformanceReportUseCaseonFailure(error: Throwable): Result<CrossTeamPerformanceReport>{
        return Result.failure(error)
    }


}