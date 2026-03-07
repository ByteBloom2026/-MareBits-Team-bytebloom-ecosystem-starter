package data.repository
import domain.model.Mentee
import data.EcoSystemDataSource
import data.repository.mappers.toDomain
import domain.validation.ValidationResult
class MenteeRepositoryImpl(
    private val dataSource: EcoSystemDataSource
) : MenteeRepository {
    override fun getAllMentees(): Result<List<Mentee>> {
        val rows = dataSource.getMentees().getOrElse { return Result.failure(it) }
        val out = mutableListOf<Mentee>()
        for (row in rows) {
            when (val mapped = row.toDomain()) {
                is ValidationResult.Success -> out.add(mapped.data)
                is ValidationResult.Failure -> { }
            }
        }
        return Result.success(out)
    }
    override fun getMenteeById(id: String): Result<Mentee?> {
        val row = dataSource.getMenteeById(id).getOrElse { return Result.failure(it) }
            ?: return Result.success(null)
        return when (val mapped = row.toDomain()) {
            is ValidationResult.Success -> Result.success(mapped.data)
            is ValidationResult.Failure -> Result.success(null)
        }
    }
    override fun getMenteesByTeamId(teamId: String): Result<List<Mentee>> {
        val rows = dataSource.getMenteesByTeamId(teamId).getOrElse { return Result.failure(it) }

        val out = mutableListOf<Mentee>()
        for (row in rows) {
            when (val mapped = row.toDomain()) {
                is ValidationResult.Success -> out.add(mapped.data)
                is ValidationResult.Failure -> { }
            }
        }
        return Result.success(out)
    }
}


