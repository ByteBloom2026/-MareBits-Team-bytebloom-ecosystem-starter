package data.validator
import data.exception.ValidationException
import java.io.File

class FilePathValidator : Validator<String> {
    override fun validate(input: String): Result<String> {
        if (input.isBlank()) {
            return Result.failure(
                ValidationException("File path is blank")
            )
        }
        val file = File(input)
        if (!file.exists()) {
            return Result.failure(
                ValidationException("File does not exist: $input")
            )
        }
        if (!file.isFile) {
            return Result.failure(
                ValidationException("Path is not a file: $input")
            )
        }
        if (file.length() == 0L) {
            return Result.failure(
                ValidationException("File is empty: $input")
            )
        }
        return Result.success(input)
    }
}