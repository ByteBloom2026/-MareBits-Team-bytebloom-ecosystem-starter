package data.validator

class NoEmptyColumnsValidator: Validator<List<String>> {
    override fun validate(input: List<String>): Result<List<String>> {
        return if (input.any { it.isBlank() }) {
            Result.failure(IllegalArgumentException("One or more columns are empty"))
        } else {
            Result.success(input)
        }
    }
}