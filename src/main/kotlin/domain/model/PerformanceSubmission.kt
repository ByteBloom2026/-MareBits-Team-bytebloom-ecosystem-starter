package domain.model
import domain.model.Team
import domain.validation.validators.*

data class PerformanceSubmission private constructor(
    val id: String,
    val type: SubmissionType,
    val score: Double,
    val menteeId: String
) {

    companion object {
        private val submissionIdValidator = SubmissionIdValidator()
        private val submissionTypeValidator = SubmissionTypeValidator()
        private val scoreValidator = ScoreValidator()
        private val menteeIdValidator = MenteeIdValidator()

        fun create(id: String, type: SubmissionType, score: Double, menteeId: String): PerformanceSubmission {
            val submissionId = submissionIdValidator.validate(id)
            val type = submissionTypeValidator.validate(type)
            val score = scoreValidator.validate(score)
            val menteeId = menteeIdValidator.validate(menteeId)
            return PerformanceSubmission(
                    id = submissionId,
                    type = type,
                    score = score,
                    menteeId = menteeId
                )

        }
    }
    enum class SubmissionType {
        TASK, WORKSHOP, BOOK_CLUB
    }
}







