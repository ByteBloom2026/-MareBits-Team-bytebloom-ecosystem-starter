package data.validator
import java.io.File
class FilePathValidator: Validator<String> {
    override fun validate(input: String): Result<String> {
        if (input.isBlank()) {
            return Result.failure(
                IllegalArgumentException("File path is blank")
            )
        }
        val file = File(input)
        if (!file.exists()) {
            return Result.failure(
                IllegalArgumentException("File does not exist: $input")
            )
        }
        if (!file.isFile) {
            return Result.failure(
                IllegalArgumentException("Path is not a file: $input")
            )
        }
        if (file.length() == 0L) {
            return Result.failure(
                IllegalArgumentException("File is empty: $input")
            )
        }
        return Result.success(input)
    }
}