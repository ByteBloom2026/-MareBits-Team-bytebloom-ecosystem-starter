package data.validator
import data.exception.ValidationException

class ColumnsCountValidator(private val expectedColumns: Int) : Validator<List<String>> {
    override fun validate(input: List<String>): Result<List<String>> {
        return if (input.size == expectedColumns) {
            Result.success(input)
        } else {
            Result.failure(
                ValidationException(
                    "Wrong number of columns: expected=$expectedColumns, got=${input.size}"
                )
            )
        }
    }
}