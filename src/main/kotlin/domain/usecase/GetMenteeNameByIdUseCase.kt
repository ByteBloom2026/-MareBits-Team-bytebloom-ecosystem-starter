package domain.usecase
import data.repository.MenteeRepository
import domain.usecase.request.RequestMenteeId

class GetMenteeNameByIdUseCase(
    private val menteeRepository: MenteeRepository
) {

     operator fun invoke(id: RequestMenteeId): String? {
        val mentee = menteeRepository.getMenteeById(id)
        return mentee?.name

    }

}