package data.datasource.model.exception

class InvalidColumnCountException : Exception("Invalid number of columns")
class BlankFilePathException : Exception("File path cannot be empty")
class FileDoesNotExistException: Exception("File does not exit")
class PathIsNotFileException : Exception("The given path is not a file")
class FileIsEmptyException : Exception("The file is empty")
class EmptyLineException : Exception("Input line must not be empty")
class EmptyColumnException: Exception("One or more columns are empty")