package data.repository.mappers
import data.datasource.model.PerformanceRow
import domain.model.PerformanceSubmission
import domain.validation.ValidationResult

fun PerformanceRow.toDomain(): ValidationResult<PerformanceSubmission> =
    PerformanceSubmission.create(
        id = id,
        type = type,
        score = score,
        menteeId = menteeId
    )
