package data.validator

class LineIsNotEmptyValidator: Validator<String>  {
    override fun validate(input: String): Result<String> {
        return if (input.isNotBlank()) {
            Result.success(input)
        } else {
            Result.failure(IllegalArgumentException("CSV line is empty"))
        }
    }
}