package domain.usecase
import com.google.common.truth.Truth.assertThat
import di_test.testModule
import domain.usecase.request.GenerateTeamAttendanceReportRequest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
class GenerateTeamAttendanceReportUseCaseTest: KoinTest {
    private val generateTeamAttendanceReportUseCase: GenerateTeamAttendanceReportUseCase by inject()
    @BeforeEach
    fun setup(){
        startKoin { modules(testModule) }
    }
    @AfterEach
    fun tearDown(){
        stopKoin()
    }
    @Test
    fun `should calculate absence counts for mentees in a team`() {
        //Given
        val request = GenerateTeamAttendanceReportRequest("marebits")
        //When
        val result = generateTeamAttendanceReportUseCase(request)
        //Then
        assertThat(result.getOrNull()).isEqualTo(mapOf("Ahmad" to 1))
    }
    @Test
    fun `should return absence counts for multiple mentees in team Hashira`() {
        //Given
        val request = GenerateTeamAttendanceReportRequest("hashira")
        //When
        val result = generateTeamAttendanceReportUseCase(request)
        //Then
        val expectedReport = mapOf("Sara" to 2)
        assertThat(result.getOrNull()).isEqualTo(expectedReport)
    }
}