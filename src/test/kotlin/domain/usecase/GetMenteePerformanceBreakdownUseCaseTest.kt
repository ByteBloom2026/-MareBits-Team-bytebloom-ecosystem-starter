package domain.usecase

import com.google.common.truth.Truth.assertThat
import data.repository.PerformanceRepository
import domain.model.PerformanceSubmission
import domain.usecase.request.GetMenteePerformanceBreakdownRequest
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test

class GetMenteePerformanceBreakdownUseCaseTest {
    private val performanceRepository = mockk<PerformanceRepository>()
    private val useCase = GetMenteePerformanceBreakdownUseCase(performanceRepository)

    @Test
    fun `should calculate average for submission type`(){
        //Given
        val menteeId = "m999"
        val request = GetMenteePerformanceBreakdownRequest(menteeId)
        val submissions = listOf(
            PerformanceSubmission.create("sub555", PerformanceSubmission.SubmissionType.TASK, 60.0, menteeId),
            PerformanceSubmission.create("sub666", PerformanceSubmission.SubmissionType.TASK, 80.0, menteeId),
            PerformanceSubmission.create("sub777", PerformanceSubmission.SubmissionType.TASK, 100.0, menteeId),
            PerformanceSubmission.create("sub888", PerformanceSubmission.SubmissionType.BOOK_CLUB, 95.0, menteeId),
            PerformanceSubmission.create("sub999", PerformanceSubmission.SubmissionType.BOOK_CLUB, 85.0, menteeId)
        )
        every { performanceRepository.getPerformanceByMenteeId(menteeId) } returns Result.success(submissions)

        //When
        val result = useCase(request)

        //Then
        val expectedBreakdown = mapOf(PerformanceSubmission.SubmissionType.TASK to 100.0,
        PerformanceSubmission.SubmissionType.BOOK_CLUB to 90.0)

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(expectedBreakdown)

       // assertThat(result.getOrNull()?.size).isEqualTo(2)
    }
    @Test
    fun `should return failure when performance repository fails`() {
        //Given
        val menteeId = "m999"
        val request = GetMenteePerformanceBreakdownRequest(menteeId)
        val errorException = Exception("Database Connection Failed")

        every { performanceRepository.getPerformanceByMenteeId(menteeId) } returns Result.failure(errorException)

        //When
        val result = useCase(request)

        //Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(errorException)
    }
}