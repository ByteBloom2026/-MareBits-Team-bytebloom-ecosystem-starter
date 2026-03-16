package data.validator
import data.datasource.model.exception.*

class LineIsNotEmptyValidator : Validator<String> {
    override fun validate(input: String): String {
        return if (input.isNotBlank()) {
            return input
        } else {
         throw EmptyLineException()
        }
    }
}