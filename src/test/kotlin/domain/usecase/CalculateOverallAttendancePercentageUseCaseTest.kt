package domain.usecase
import com.google.common.truth.Truth.assertThat
import di.testModule
import domain.usecase.request.CalculateOverallAttendancePercentageRequest
import fakeRepository.FakeAttendanceRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
class CalculateOverallAttendancePercentageUseCaseTest : KoinTest{
    private val calculateOverallAttendancePercentageUseCase: CalculateOverallAttendancePercentageUseCase by inject()
    @BeforeEach
    fun setUp() {
        startKoin { modules(testModule) }
    }
    @AfterEach
    fun tearDown() { stopKoin() }
    @Test
    fun `shouldCalculateOverallAttendancePercentage`() {
        val request = CalculateOverallAttendancePercentageRequest("m001")
        val result = calculateOverallAttendancePercentageUseCase(request)
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(66.67)
    }
    @Test
    fun `shouldCalculateAttendancePercentageWithLateAsHalfPoint`() {
        val request = CalculateOverallAttendancePercentageRequest("m004")
        val result = calculateOverallAttendancePercentageUseCase(request)
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(50.0)
    }
}