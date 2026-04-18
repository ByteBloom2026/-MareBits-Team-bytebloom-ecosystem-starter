package domain.usecase

import com.google.common.truth.Truth.assertThat
import data.repository.PerformanceRepository
import di_test.testModule
import domain.model.PerformanceSubmission
import domain.usecase.request.GetMenteePerformanceBreakdownRequest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test

class GetMenteePerformanceBreakdownUseCaseTest: KoinTest {
    private val useCase : GetMenteePerformanceBreakdownUseCase by inject()
    @BeforeEach
    fun setup(){
        startKoin { modules(testModule) }
    }
    @AfterEach
    fun tearDown(){
        stopKoin()
    }
    @Test
    fun `should calculate average for submission type`(){
        //Given
        val menteeId = "m001"
        val request = GetMenteePerformanceBreakdownRequest(menteeId)
        val expectedBreakdown = mapOf(PerformanceSubmission.SubmissionType.TASK to 92.5)
        //When
        val result = useCase(request)
        //Then
        assertThat(result.getOrNull()).isEqualTo(expectedBreakdown)
    }
    @Test
    fun `should return failure when performance repository fails`() {
        //Given
        val request = GetMenteePerformanceBreakdownRequest("mentee")
        //When
        val result = useCase(request)
        //Then
        assertThat(result.getOrNull()).isEmpty()
    }
}