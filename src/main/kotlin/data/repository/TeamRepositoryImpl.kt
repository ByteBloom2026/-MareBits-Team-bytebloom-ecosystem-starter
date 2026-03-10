package data.repository
import domain.model.Team
import data.EcoSystemDataSource
import data.repository.mappers.toDomain

class TeamRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : TeamRepository {
    override fun getAllTeams(): Result<List<Team>> {
        val rows = dataSource.getTeams()
            .getOrElse { return Result.failure(it)}
                .map { it.toDomain()
            }
        return Result.success(rows)
    }
    override fun searchTeamsByName(keyword: String): Result<List<Team>>{
        val rows = dataSource.getTeams()
            .getOrElse { return Result.failure(it) }
            .filter { it.name.contains(keyword, ignoreCase = true) }
            .map { it.toDomain() }
        return Result.success(rows)
    }
    override fun getTeamById(teamId: String): Result<Team?> {
        val rows = dataSource.getTeamById(teamId)
            .getOrElse { return Result.failure(it) }
            ?: return Result.success(null)
        val team =rows.toDomain()
        return Result.success(team)
    }
    override fun getMentorLeadByTeamId(teamId: String): Result<String?> {
        val team = getTeamById(teamId).getOrElse { return Result.failure(it) }

        return Result.success(team?.mentorLead)
    }
}