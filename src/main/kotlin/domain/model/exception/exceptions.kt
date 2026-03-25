package domain.model.exception

sealed class SearchTeamException(string: String) : Throwable() {
    class EmptyTeamNameException: SearchTeamException("Team name cannot be empty")
    class InvalidTeamNameLengthException: SearchTeamException("Team name length is invalid")
    class EmptyTeamIdExcpection :SearchTeamException("The team id cannect be empty")
}
sealed class MenteeException (string: String): Throwable(){
    class EmptyMenteeNameException: MenteeException("Mentee name cannot be empty")
    class InvalidMenteeNameException :MenteeException(" The mentee name is invalid ")
}
sealed class AttendanceException(string: String): Throwable(){
    class EmptyAttendanceWeeksException : AttendanceException("Attendance weeks cannot be empty")
}
sealed class SubmissionException(string: String): Throwable(){
    class InvalidSubmissionTypeException : SubmissionException("The submission type is invalid ")
    class EmptySumissionTypeException : SubmissionException("The submission type cannot is empty ")
    class InvalidSumbmissionIdException : SubmissionException("The submission Id is invalid")
    class EmptySubmissionIdException:SubmissionException("The submission Id  cannot is empty")
}
sealed class ProjectException(string: String): Throwable(){
    class EmptyProjectNameException : ProjectException("project name cannot be empty")
    class InvalidProjectNameLengthException : ProjectException("Project length name is invalid")
    class InvalidProjectIdException : ProjectException("The project id is invalid ")
}
sealed class ScoreException(string: String): Throwable(){
    class InvalidScoreException : ScoreException("Score is invalid (NaN or Infinite)")
    class ScoreOutOfRangeException(val min : Double,val max : Double) : ScoreException("the score is out of range [$min,$max]")
}
sealed class QueryException(string: String): Throwable(){
    class EmptyQueryException : QueryException("Search query cannot be empty")
    class InvalidQueryException :QueryException("Search query is invalid")
}
class NotCapitalizedNameException: Exception("Name must start with a letter")
class NameContainsInvalidCharactersException :Exception("Name must contain only letters")
sealed class DataAccessException(message: String) : Exception(message) {
    class InvalidDataSourceException : DataAccessException("Invalid data source")
    class InvalidDataFormatException : DataAccessException("Invalid data format")
}