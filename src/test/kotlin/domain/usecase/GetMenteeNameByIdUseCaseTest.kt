package domain.usecase

import com.google.common.truth.Truth.assertThat
import data.repository.MenteeRepository
import domain.model.Mentee
import domain.usecase.request.GetMenteeNameByIdRequest
import io.mockk.every
import io.mockk.mockk
import org.junit.runner.Request
import kotlin.test.Test

class GetMenteeNameByIdUseCaseTest {
    private val menteeRepository = mockk<MenteeRepository>()
    private val useCase = GetMenteeNameByIdUseCase(menteeRepository)

    @Test
    fun `should return mentee name when mentee exists`() {
        // Given
        val menteeId = "m123"
        val expectedName = "Elham Hassan"
        val request = GetMenteeNameByIdRequest(menteeId)
        val mockMentee = Mentee.create(id = menteeId, name = expectedName, teamId = "marebits")
        every { menteeRepository.getMenteeById(menteeId) } returns Result.success(mockMentee)

        // When
        val result = useCase(request)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(expectedName)
    }

    @Test
    fun `should return failure when mentee does not exist`() {
        // Given
        val menteeId = "unknown_id"
        val request = GetMenteeNameByIdRequest(menteeId)
        val errorException = Exception("Mentee not found")

        every { menteeRepository.getMenteeById(menteeId) } returns Result.failure(errorException)

        // When
        val result = useCase(request)

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(errorException)

    }
}