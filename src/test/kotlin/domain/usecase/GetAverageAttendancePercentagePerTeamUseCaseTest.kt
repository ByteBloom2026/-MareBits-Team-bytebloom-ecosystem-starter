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
    fun `should calculate average attendance for teams`(){
        //When
        val result = useCase()
        //Then
        val report = result.getOrNull()
        assertThat(report).isNotNull()
        assertThat(report!!["Marebits"]).isWithin(0.01).of(66.66)
        assertThat(report["Hashira"]).isWithin(0.01).of(33.33)
    }
    @Test
    fun `should calulate attendance percentage for Hashira`(){
        //When
        val result = useCase().getOrNull()
        //Then
        assertThat(result).isNotNull()
        assertThat(result?.get("Hashira")).isWithin(0.01).of(33.33)
    }
}