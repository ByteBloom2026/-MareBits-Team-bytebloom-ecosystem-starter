package data.validator
import data.datasource.model.exception.*

class ColumnsCountValidator(private val expectedColumns: Int) : Validator<List<String>> {
    override fun validate(input: List<String>): List<String>{
        return if (input.size == expectedColumns) {
            return input
        } else {
             throw InvalidColumnCountException()

        }
    }
}