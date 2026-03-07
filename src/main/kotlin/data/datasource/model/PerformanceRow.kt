package data.datasource.model

import domain.model.PerformanceSubmission.SubmissionType

data class PerformanceRow(
    val id: String,
    val type: SubmissionType,
    val score: Double,
    val menteeId: String
)

