package domain.usecase

import data.repository.MenteeRepository
import domain.model.exception.MenteeNotFoundException

class GetMenteeNameByIdUseCase(
    private val menteeRepository: MenteeRepository
) {

    operator fun invoke(id: String): Result<String> {
        val mentee = menteeRepository.getMenteeById(id)
        mentee.getOrNull()?.let {
            return Result.success(it.name)
        }
        return Result.failure(MenteeNotFoundException())
    }

}