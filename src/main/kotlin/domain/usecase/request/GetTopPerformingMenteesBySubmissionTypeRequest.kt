package domain.usecase.request
import domain.model.PerformanceSubmission
data class GetTopPerformingMenteesBySubmissionTypeRequest (
    val submissionType: PerformanceSubmission.SubmissionType
)