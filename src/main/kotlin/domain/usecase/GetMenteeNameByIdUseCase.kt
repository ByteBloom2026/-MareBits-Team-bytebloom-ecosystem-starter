package domain.usecase
import data.repository.MenteeRepository
import domain.usecase.request.GetMenteeNameByIdRequest
import domain.model.Mentee
class GetMenteeNameByIdUseCase(
    private val menteeRepository: MenteeRepository
) {
    operator fun invoke(request: GetMenteeNameByIdRequest):  Result<String?>{
        return menteeRepository
            .getMenteeById(request.menteeId)
            .fold(
                onSuccess = ::onGetMenteeNameByIdSuccess,
                onFailure = ::onGetMenteeNameByIdFailure
            )

    }
    private fun onGetMenteeNameByIdSuccess(mentee: Mentee?): Result<String?> {
        return Result.success(mentee?.name)
    }
    private fun  onGetMenteeNameByIdFailure(error: Throwable): Result<String?> {
        return Result.failure(error)
    }
}//