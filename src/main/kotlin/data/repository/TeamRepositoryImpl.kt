package data.repository
import domain.model.Team
import data.EcoSystemDataSource
import data.repository.mappers.toDomain
import domain.model.exception.DataAccessException
class TeamRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : TeamRepository {
    override fun getAllTeams(): Result<List<Team>> {
        return dataSource.getTeams().fold(
            onSuccess = { teamRows ->
                Result.success(
                    teamRows.map { row -> row.toDomain() }
                )
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    override fun searchTeamsByName(keyword: String): Result<List<Team>>{
        return dataSource.getTeams().fold(
            onSuccess = { teamRows ->
                val filtered = teamRows
                    .filter { it.name.contains(keyword, ignoreCase = true) }
                    .map { row -> row.toDomain() }

                Result.success(filtered)
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    override fun getTeamById(teamId: String): Result<Team?> {
        return dataSource.getTeamById(teamId).fold(
            onSuccess = { teamRow ->
                if (teamRow == null) {
                    Result.success(null)
                } else {
                    Result.success(teamRow.toDomain())
                }
            },
            onFailure = { exception ->
                Result.failure(mapToDomainException(exception))
            }
        )
    }
    override fun getMentorLeadByTeamId(teamId: String): Result<String?> {
        return getTeamById(teamId).fold(
            onSuccess = { team ->
                Result.success(team?.mentorLead)
            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )
    }
    private fun mapToDomainException(exception: Throwable): Throwable {
        return DataAccessException.DataUnavailableException()
    }
}