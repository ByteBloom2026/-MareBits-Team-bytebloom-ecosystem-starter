package domain.usecase
import di_test.testModule
import domain.usecase.request.GenerateTeamAttendanceReportRequest
import kotlin.test.Test
import io.mockk.verify
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.koin.core.context.stopKoin
import data.repository.*
import com.google.common.truth.Truth.assertThat




class GenerateCrossTeamPreformanceRepotUseCaseTest : KoinTest {
    val generateCrossTeamPreformanceReportUseCase: GenerateCrossTeamPreformanceReportUseCase by inject()
    private val teamRepo: TeamRepository by inject()
    private val perfRepo: PerformanceRepository by inject()

    @BeforeEach
    fun setup() {
        startKoin {
            modules(
                testModule
            )
        }
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
    }


    @Test
    fun `should Return Correct Performance Score For Given Team`() {
        // Given
        val request = GenerateTeamAttendanceReportRequest("marebits")
        // When
        val result = generateCrossTeamPreformanceReportUseCase(request)
        val report = result.getOrNull()
        // Then
        assertThat(report?.ScoreAndTeamName?.firstOrNull()?.Score)
            .isEqualTo(185.0)
    }

    @Test
    fun `sholde return success result with correct data when repositories succeed`() {
        // Given
        val request = GenerateTeamAttendanceReportRequest("marebits")
        //When
        val generateCrossTeamPreformanceReportUseCaseOnSuccess = generateCrossTeamPreformanceReportUseCase(request)
        //Then
        assert(generateCrossTeamPreformanceReportUseCaseOnSuccess.isSuccess)
    }

    @Test
    fun `Should return error result when repositories fail`() {
        // Given
        val request = GenerateTeamAttendanceReportRequest("marebits")
        //When
        val generateCrossTeamPreformanceReportUseCaseOnSuccess = generateCrossTeamPreformanceReportUseCase(request)
        //Then
        assert(generateCrossTeamPreformanceReportUseCaseOnSuccess.isFailure)
    }

    @Test
    fun `should fetch performance data when invoke is called`() {
        //When
        generateCrossTeamPreformanceReportUseCase
            .invoke(request = GenerateTeamAttendanceReportRequest(teamId = String()))
        //Then
        verify { perfRepo.getPerformanceByTeamId(teamId = String()) }
    }

    @Test
    fun `shold fetch teams data when invoke is called`() {
        //When
        generateCrossTeamPreformanceReportUseCase
            .invoke(request = GenerateTeamAttendanceReportRequest(teamId = String()))
        //Then
        verify { teamRepo.getTeamById(teamId = String()) }

    }

}

