package domain.usecase
import data.repository.TeamRepository
class CountTeamsByMentorUseCase(private val teamRepository: TeamRepository) {
    operator fun invoke(): Result<Map<String, Int>> {
        return Result.success(
            teamRepository.getAllTeams()
                .groupBy { it.mentorLead }
                .mapValues { (_, teams) -> teams.size }
        )
    }
}