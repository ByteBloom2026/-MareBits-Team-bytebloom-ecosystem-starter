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
class GetTopPerformingMenteesBySubmissionTypeUseCaseTest : KoinTest {
    private val getTopPerformingMenteesBySubmissionTypeUseCase: GetTopPerformingMenteesBySubmissionTypeUseCase by inject()

    @BeforeEach
    fun setUp() {
        startKoin { modules(testModule) }
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should Return Top Performing Mentee For Task Submission Type`() {
        //given
        val request = GetTopPerformingMenteesBySubmissionTypeRequest(
            PerformanceSubmission.SubmissionType.TASK
        )
        //when
        val result = getTopPerformingMenteesBySubmissionTypeUseCase(request)
        val returnedMenteeIds = result.getOrNull()?.map { it.id }
        //then
        assertThat(returnedMenteeIds).containsExactly("m001")
    }

    @Test
    fun `should return m003 as top performing mentee for book club submission type`() {
        //given
        val request = GetTopPerformingMenteesBySubmissionTypeRequest(
            PerformanceSubmission.SubmissionType.BOOK_CLUB
        )
        //when
        val result = getTopPerformingMenteesBySubmissionTypeUseCase(request)
        val returnedMenteeIds = result.getOrNull()?.map { it.id }
        //then
        assertThat(returnedMenteeIds).containsExactly("m003")
    }

    @Test
    fun `should return m003 as top performing mentee for workshop submission type`() {
       //given
        val request = GetTopPerformingMenteesBySubmissionTypeRequest(
            PerformanceSubmission.SubmissionType.WORKSHOP)
        //when
        val result = getTopPerformingMenteesBySubmissionTypeUseCase(request)
        val returnedMenteeIds = result.getOrNull()?.map { it.id }
        //then
        assertThat(returnedMenteeIds).containsExactly("m003")

    }
}