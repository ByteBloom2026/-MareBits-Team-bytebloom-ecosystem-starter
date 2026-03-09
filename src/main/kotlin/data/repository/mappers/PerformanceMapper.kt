package data.repository.mappers
import data.datasource.model.PerformanceRow
import domain.model.PerformanceSubmission


fun PerformanceRow.toDomain():PerformanceSubmission =
    PerformanceSubmission.create(
        id = id,
        type = type,
        score = score,
        menteeId = menteeId
    )
