package domain.usecase
import com.google.common.truth.Truth.assertThat
import di_test.testModule
import domain.model.PerformanceSubmission
import domain.usecase.request.GetTopPerformingMenteesBySubmissionTypeRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
class GetTopPerformingMenteesBySubmissionTypeUseCaseTest : KoinTest{
    private val getTopPerformingMenteesBySubmissionTypeUseCase: GetTopPerformingMenteesBySubmissionTypeUseCase by inject()
    @BeforeEach
    fun setUp() { startKoin { modules(testModule) } }
    @AfterEach
    fun tearDown() { stopKoin() }

    @Test
    fun `shouldReturnTopPerformingMenteeForTaskSubmissionType`() {

        val request = GetTopPerformingMenteesBySubmissionTypeRequest(
            PerformanceSubmission.SubmissionType.TASK
        )
        val result =getTopPerformingMenteesBySubmissionTypeUseCase (request)
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()?.map { it.id }).containsExactly("m001")
    }
}