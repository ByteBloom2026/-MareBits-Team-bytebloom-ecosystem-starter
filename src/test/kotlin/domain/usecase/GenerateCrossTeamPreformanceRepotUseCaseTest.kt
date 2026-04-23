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
import io.mockk.mockk
import io.mockk.every




class GenerateCrossTeamPreformanceRepotUseCaseTest : KoinTest {
    val generateCrossTeamPreformanceReportUseCase: GenerateCrossTeamPreformanceReportUseCase by inject()

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
            .isEqualTo(1560.0)
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
    fun `should fetch teams data when invoke is called`() {
        // Given
        val teamRepoMocck = mockk<TeamRepository>()
        val totalScore = mockk<TotalScore>()
        val useCase = GenerateCrossTeamPreformanceReportUseCase(
            teamRepoMocck,
            totalScore
        )
        every { teamRepoMocck.getAllTeams() } returns Result.success(emptyList())
        // When
        useCase.invoke(
            GenerateTeamAttendanceReportRequest(teamId = "team1")
        )
        // Then
        verify { teamRepoMocck.getAllTeams() }
    }

}

