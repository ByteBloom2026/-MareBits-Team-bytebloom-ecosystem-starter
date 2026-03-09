package data.repository
import domain.model.Mentee
import data.EcoSystemDataSource
import data.repository.mappers.toDomain

class MenteeRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : MenteeRepository {
    override fun getAllMentees(): Result<List<Mentee>> {
        val rows = dataSource.getMentees()
            .getOrElse { return Result.failure(it) }
            .map { it.toDomain()}
        return Result.success(rows)
    }
    override fun getMenteeById(id: String): Result<Mentee?> {
        val row = dataSource.getMenteeById(id)
            .getOrElse { return Result.failure(it) }
            ?: return Result.success(null)
        val mentee=row.toDomain()
        return Result.success(mentee)
    }
    override fun getMenteesByTeamId(teamId: String): Result<List<Mentee>> {
        val rows = dataSource.getMenteesByTeamId(teamId)
            .getOrElse { return Result.failure(it) }
            .map { it.toDomain() }
        return Result.success(rows)
    }
}


