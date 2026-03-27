package domain.model.exception

sealed class SearchTeamException(message: String) : Exception(message){
    class EmptyTeamNameException: SearchTeamException("Team name cannot be empty")
    class InvalidTeamNameLengthException: SearchTeamException("Team name length is invalid")
    class EmptyTeamIdExcpection :SearchTeamException("The team id cannect be empty")
}
sealed class MenteeException (message: String): Exception(message){
    class EmptyMenteeNameException: MenteeException("Mentee name cannot be empty")
    class InvalidMenteeNameException :MenteeException(" The mentee name is invalid ")
}
sealed class AttendanceException(message: String): Exception(message){
    class EmptyAttendanceWeeksException : AttendanceException("Attendance weeks cannot be empty")
    class InvalidAttendanceStateException : AttendanceException("Invalid attendance state")
}
sealed class SubmissionException(message: String): Exception(message){
    class InvalidSubmissionTypeException : SubmissionException("The submission type is invalid ")
    class EmptySumissionTypeException : SubmissionException("The submission type cannot is empty ")
    class InvalidSumbmissionIdException : SubmissionException("The submission Id is invalid")
    class EmptySubmissionIdException:SubmissionException("The submission Id  cannot is empty")
}
sealed class ProjectException(message: String): Exception(message){
    class EmptyProjectNameException : ProjectException("project name cannot be empty")
    class InvalidProjectNameLengthException : ProjectException("Project length name is invalid")
    class InvalidProjectIdException : ProjectException("The project id is invalid ")
}
sealed class ScoreException(message: String): Exception(message){
    class InvalidScoreException : ScoreException("Score is invalid (NaN or Infinite)")
    class ScoreOutOfRangeException(val min : Double,val max : Double) : ScoreException("the score is out of range [$min,$max]")
}
sealed class QueryException(message: String): Exception(message){
    class EmptyQueryException : QueryException("Search query cannot be empty")
    class InvalidQueryException :QueryException("Search query is invalid")
}
class NotCapitalizedNameException: Exception("Name must start with a letter")
class NameContainsInvalidCharactersException :Exception("Name must contain only letters")
sealed class DataAccessException(message: String) : Exception(message) {
    class DataUnavailableException : DataAccessException("Data is unavailable")
}
