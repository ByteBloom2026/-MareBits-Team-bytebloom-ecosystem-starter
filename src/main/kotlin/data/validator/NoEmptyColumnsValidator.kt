package data.validator
import data.validator.exception.*

class NoEmptyColumnsValidator : Validator<List<String>> {
    override fun validate(input: List<String>):List<String> {
        return if (input.any { it.isBlank() }) {
             throw EmptyColumnException()
        } else {
            return input
        }
    }
}