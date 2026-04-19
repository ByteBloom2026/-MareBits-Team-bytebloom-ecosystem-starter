package domain.usecase
import com.google.common.truth.Truth.assertThat
import di_test.testModule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
class GetAverageAttendancePercentagePerTeamUseCaseTest: KoinTest {
    private val useCase : GetAverageAttendancePercentagePerTeamUseCase by inject()
    @BeforeEach
    fun setup(){
        startKoin { modules(testModule) }
    }
    @AfterEach
    fun tearDown(){
        stopKoin()
    }
    @Test
    fun `should calculate average attendance for teams`() {
        //When
        val result = useCase().getOrNull()
        //Then
        assertThat(result?.get("Marebits")).isWithin(0.01).of(66.66)

    }
    @Test
    fun `should calulate attendance percentage for Hashira`(){
        //When
        val result = useCase().getOrNull()
        //Then
        assertThat(result?.get("Hashira")).isWithin(0.01).of(33.33)
    }
    @Test
    fun `should return zero for teams with no attendance "Kernels"`(){
        //When
        val result = useCase().getOrNull()
        //Then
        assertThat(result?.get("Kernels")).isEqualTo(0.0)
    }
}