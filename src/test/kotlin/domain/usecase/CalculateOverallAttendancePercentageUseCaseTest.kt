package domain.usecase
import com.google.common.truth.Truth.assertThat
import domain.usecase.request.CalculateOverallAttendancePercentageRequest
import fakeRepository.FakeAttendanceRepository
import org.junit.jupiter.api.Test
class CalculateOverallAttendancePercentageUseCaseTest {
    @Test
    fun shouldCalculateOverallAttendancePercentage() {
        val repository = FakeAttendanceRepository()
        val useCase = CalculateOverallAttendancePercentageUseCase(repository)
        val request = CalculateOverallAttendancePercentageRequest("m001")
        val result = useCase(request)
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(66.67)
    }
    @Test
    fun shouldCalculateAttendancePercentageWithLateAsHalfPoint() {
        val repository = FakeAttendanceRepository()
        val useCase = CalculateOverallAttendancePercentageUseCase(repository)
        val request = CalculateOverallAttendancePercentageRequest("m004")
        val result = useCase(request)
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(50.0)
    }
}