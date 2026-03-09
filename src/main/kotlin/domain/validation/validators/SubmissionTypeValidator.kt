package domain.validation.validators
import domain.model.PerformanceSubmission
import domain.validation.EcosystemValidator
import domain.model.exception.*

class SubmissionTypeValidator: EcosystemValidator<PerformanceSubmission.SubmissionType> {
    private val allowedTypes =listOf("TASK","BOOK_CLUB","WORKSHOP")
   override fun validate(data: PerformanceSubmission.SubmissionType): PerformanceSubmission.SubmissionType {
        val value=data.name.trim().uppercase()
        if(value.isEmpty()){
            throw EmptySumissionTypeException()
        }
        if (!allowedTypes.contains(value)){
            throw InvalidSubmissionTypeException()
        }
        return data

    }
}



