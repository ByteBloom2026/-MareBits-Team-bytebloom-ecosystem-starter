package domain.validation.validators
import domain.model.PerformanceSubmission
import domain.validation.ValidationResult
import domain.validation.Validator

class SubmissionTypeValidator: Validator<PerformanceSubmission.SubmissionType> {
    private val allowedTypes =listOf("TASK","BOOK_CLUB","WORKSHOP")
   override fun validate(data: PerformanceSubmission.SubmissionType): ValidationResult<PerformanceSubmission.SubmissionType> {
        val value=data.name.trim().uppercase()
        if(value.isEmpty()){
            return ValidationResult.failure("submissionType", "Submission type is required.")
        }
        if (!allowedTypes.contains(value)){
            return ValidationResult.failure(
                "submissionType",
                "Invalid type. Allowed types are: ${allowedTypes.joinToString(", ")}"
            )
        }
        return ValidationResult.success(data)
    }
}



