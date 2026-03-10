package data.validator
import data.exception.ValidationException

class LineIsNotEmptyValidator : Validator<String> {
    override fun validate(input: String): Result<String> {
        return if (input.isNotBlank()) {
            Result.success(input)
        } else {
            Result.failure(
                ValidationException("CSV line is empty")
            )
        }
    }
}