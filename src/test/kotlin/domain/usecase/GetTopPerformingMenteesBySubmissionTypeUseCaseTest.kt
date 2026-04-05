package domain.usecase
import com.google.common.truth.Truth.assertThat
import domain.model.PerformanceSubmission
import domain.usecase.request.GetTopPerformingMenteesBySubmissionTypeRequest
import fakeRepository.FakeMenteeRepository
import fakeRepository.FakePerformanceRepository
import org.junit.jupiter.api.Test
class GetTopPerformingMenteesBySubmissionTypeUseCaseTest {
    @Test
    fun shouldReturnTopPerformingMenteeForTaskSubmissionType() {
        val menteeRepository = FakeMenteeRepository()
        val performanceRepository = FakePerformanceRepository()
        val useCase = GetTopPerformingMenteesBySubmissionTypeUseCase(
            menteeRepository,
            performanceRepository
        )
        val request = GetTopPerformingMenteesBySubmissionTypeRequest(
            PerformanceSubmission.SubmissionType.TASK
        )
        val result = useCase(request)
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()?.map { it.id }).containsExactly("m001")
    }
}