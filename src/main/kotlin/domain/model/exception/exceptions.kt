package domain.model.exception

sealed class SearchTeamException(string: String) : Throwable() {
    class EmptyTeamNameException: SearchTeamException("Team name cannot be empty")
    class InvalidTeamNameLengthException: SearchTeamException("Team name length is invalid")
    class EmptyTeamIdExcpection :Exception("The team id cannect be empty")
}
class EmptyMenteeNameException: Exception("Mentee name cannot be empty")
class NotCapitalizedNameException: Exception("Name must start with a letter")
class EmptyAttendanceWeeksException : Exception("Attendance weeks cannot be empty")
class EmptyProjectNameException : Exception("project name cannot be empty")
class InvalidProjectNameLengthException : Exception("Project length name is invalid")
class InvalidProjectIdException : Exception("The project id is invalid ")
class InvalidMenteeNameException :Exception(" The mentee name is invalid ")
class EmptyQueryException : Exception("Search query cannot be empty")
class InvalidQueryException :Exception("Search query is invalid")
class InvalidScoreException : Exception("Score is invalid (NaN or Infinite)")
class ScoreOutOfRangeException(val min : Double,val max : Double) : Exception("the score is out of range [$min,$max]")
class EmptySubmissionIdException:Exception("The submission Id  cannot is empty")
class InvalidSumbmissionIdException : Exception("The submission Id is invalid")
class EmptySumissionTypeException : Exception("The submission type cannot is empty ")
class InvalidSubmissionTypeException : Exception("The submission type is invalid ")
class NameContainsInvalidCharactersException :Exception("Name must contain only letters")
class MenteeNotFoundException(): Exception()
//