package domain.usecase.request

data class RequestSumbissionType(
    val sumbissionType: SubmissionType
)
enum class SubmissionType {
    TASK, WORKSHOP, BOOK_CLUB
}