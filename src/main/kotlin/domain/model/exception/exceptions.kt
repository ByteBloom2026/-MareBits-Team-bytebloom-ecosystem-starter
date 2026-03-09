package domain.model.exception


sealed class SearchTeamException : Throwable() {
    class EmptyTeamNameException: SearchTeamException()

    class InvalidTeamNameLengthException: SearchTeamException()
}

class EmptyMenteeNameException: Exception()

class NotCapitalizedNameException: Exception()
class EmptyAttendanceWeeksException : Exception()

class EmptyProjectNameException : Exception()
class InvalidProjectNameLengthException : Exception()
class InvalidProjectIdException : Exception()