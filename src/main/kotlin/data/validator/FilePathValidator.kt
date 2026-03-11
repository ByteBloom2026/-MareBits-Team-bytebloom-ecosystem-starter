package data.validator
import java.io.File
import data.datasource.model.exception.*

class FilePathValidator : Validator<String> {
    override fun validate(input: String): String {
        if (input.isBlank()) {
             throw BlankFilePathException()

        }
        val file = File(input)
        if (!file.exists()) {
           throw FileDoesNotExistException()
        }
        if (!file.isFile) {
            throw PathIsNotFileException()
        }
        if (file.length() == 0L) {
            throw FileIsEmptyException()
        }
        return  input
    }
}