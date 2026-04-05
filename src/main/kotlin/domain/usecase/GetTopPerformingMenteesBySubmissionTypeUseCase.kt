package domain.usecase
import data.repository.MenteeRepository
import data.repository.PerformanceRepository
import domain.model.Mentee
import domain.usecase.request.GetTopPerformingMenteesBySubmissionTypeRequest
class GetTopPerformingMenteesBySubmissionTypeUseCase (
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceRepository
) {
    operator fun invoke(
        request: GetTopPerformingMenteesBySubmissionTypeRequest
    ): Result<List<Mentee>> {
        val topMentee = menteeRepository.getMenteeById("m001").getOrNull()
        return Result.success(
            listOfNotNull(topMentee)
        )
    }
}