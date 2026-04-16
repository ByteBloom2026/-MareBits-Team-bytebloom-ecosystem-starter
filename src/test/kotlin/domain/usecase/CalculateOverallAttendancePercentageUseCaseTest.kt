package domain.usecase
import com.google.common.truth.Truth.assertThat
import di_test.testModule
import domain.usecase.request.CalculateOverallAttendancePercentageRequest
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
    fun `should Calculate Overall Attendance Percentage`() {
        //given
        val request = CalculateOverallAttendancePercentageRequest("m001")
        //when
        val result = calculateOverallAttendancePercentageUseCase(request)
        //then
        assertThat(result.getOrNull()).isEqualTo(66.67)
    }
    @Test
    fun `should Calculate Attendance Percentage With Late As Half Point`() {
        //given
        val request = CalculateOverallAttendancePercentageRequest("m004")
        //when
        val result = calculateOverallAttendancePercentageUseCase(request)
        //then
        assertThat(result.getOrNull()).isEqualTo(50.0)
    }
}