package data.repository
import domain.model.Team
import data.EcoSystemDataSource
import data.repository.mappers.toDomain
import domain.validation.ValidationResult
class TeamRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : TeamRepository {
    override fun getAllTeams(): Result<List<Team>> {
        val rows = dataSource.getTeams().getOrElse { return Result.failure(it) }

        val out = mutableListOf<Team>()
        for (row in rows) {
            when (val mapped = row.toDomain(project = null)) {
                is ValidationResult.Success -> out.add(mapped.data)
                is ValidationResult.Failure -> { }
            }
        }
        return Result.success(out)
    }

    override fun getTeamById(teamId: String): Result<Team?> {
        val row = dataSource.getTeamById(teamId).getOrElse { return Result.failure(it) }
            ?: return Result.success(null)

        return when (val mapped = row.toDomain(project = null)) {
            is ValidationResult.Success -> Result.success(mapped.data)
            is ValidationResult.Failure -> Result.success(null)
        }
    }

    override fun getMentorLeadByTeamId(teamId: String): Result<String?> {
        val team = getTeamById(teamId).getOrElse { return Result.failure(it) }
        return Result.success(team?.mentorLead)
    }
}