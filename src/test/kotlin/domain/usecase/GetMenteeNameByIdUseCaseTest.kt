package domain.usecase
import com.google.common.truth.Truth.assertThat
import di_test.testModule
import domain.usecase.request.GetMenteeNameByIdRequest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test
class GetMenteeNameByIdUseCaseTest : KoinTest {
    private val useCase : GetMenteeNameByIdUseCase by inject()
    @BeforeEach
    fun setup() {
        startKoin { modules(testModule) }
    }
    @AfterEach
    fun tearDown() {
        stopKoin()
    }
    @Test
    fun `should return mentee name when mentee exists`() {
        //Given
        val request = GetMenteeNameByIdRequest("m001")
        // When
        val result = useCase(request)
        // Then
        assertThat(result.getOrNull()).isEqualTo("Ahmad")
    }
    @Test
    fun `should return failure when mentee does not exist`() {
        // Given
        val request = GetMenteeNameByIdRequest("unknown_id")
        // When
        val result = useCase(request)
        // Then
        assertThat(result.getOrNull()).isNull()
    }
}