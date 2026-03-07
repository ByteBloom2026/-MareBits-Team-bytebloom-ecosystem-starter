package data.validator

class ColumnsCountValidator(private val expectedColumns: Int):Validator<List<String>> {
    override fun validate(input: List<String>): Result<List<String>> {
        return if (input.size == expectedColumns) {
            Result.success(input)
        } else {
            Result.failure(
                IllegalArgumentException(
                    "Wrong number of columns: expected=$expectedColumns, got=${input.size}")) }
    }
}