package domain.model
import domain.validation.ValidationResult
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

        fun create(id: String, type: SubmissionType, score: Double, menteeId: String): ValidationResult<PerformanceSubmission> {
            val idResult = submissionIdValidator.validate(id)
            if (idResult is ValidationResult.Failure)
                return idResult
            val typeResult = submissionTypeValidator.validate(type)
            if (typeResult is ValidationResult.Failure)
                return typeResult
            val scoreResult = scoreValidator.validate(score)
            if (scoreResult is ValidationResult.Failure)
                return scoreResult
            val menteeResult = menteeIdValidator.validate(menteeId)
            if (menteeResult is ValidationResult.Failure)
                return menteeResult
            return ValidationResult.success(
                PerformanceSubmission(
                    id = (idResult as ValidationResult.Success).data,
                    type = (typeResult as ValidationResult.Success).data,
                    score = (scoreResult as ValidationResult.Success).data,
                    menteeId = (menteeResult as ValidationResult.Success).data
                )
            )
        }
    }
    enum class SubmissionType {
        TASK, WORKSHOP, BOOK_CLUB
    }
}







