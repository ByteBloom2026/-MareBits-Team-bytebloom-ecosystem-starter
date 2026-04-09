package domain.usecase
import di.testModule
import io.mockk.every
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
import com.google.common.truth.ExpectFailure.assertThat


class GenerateCrossTeamPreformanceRepotUseCaseTest : KoinTest {
    private val myTestModule = testModule()
    val generateCrossTeamPreformanceReportUseCase: GenerateCrossTeamPreformanceReportUseCase by inject()
    private val teamRepo: TeamRepository by inject()
    private val perfRepo: PerformanceRepository by inject()

    @BeforeEach
    fun setup() {
        startKoin {
            modules(
                myTestModule.useCaseTestModule
                , myTestModule.repositorytestModule
            )
        }
    }
    @AfterEach
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Should calculate total  score correctly for each team `(){
        //given
        generateCrossTeamPreformanceReportUseCase
        //when
   val rsaltTheeentretheIdTeam = perfRepo.getPerformanceByTeamId("123ibt").getOrNull()

        //then
      //  assertThat(rsaltTheeentretheIdTeam).isEqualTo()
    }

    @Test
    fun `Should calculate total score correctly for each team`() {

        // given
        val performanceList = myTestModule.performance.getPerformanceByTeamId("123ibt")
            .getOrThrow() ?: emptyList()
        val expectedTotalScore = performanceList.sumOf { it.score }

        // when
        val result = generateCrossTeamPreformanceReportUseCase(GenerateTeamAttendanceReportRequest(teamId = String()))
        val teamScore = result.getOrThrow()
           val Score= teamScore.ScoreAndTeamName
               .find { it.TeamName == "Team 123ibt" }

        // then
        assertThat(teamScore?.Score).isEqualTo(expectedTotalScore)
    }


    @Test
    fun `should fetch teams and performance data when invoke is called`() {
        generateCrossTeamPreformanceReportUseCase
            .invoke(request = GenerateTeamAttendanceReportRequest(teamId = String()))
        verify { teamRepo.getTeamById(teamId = String()) }
        verify { perfRepo.getPerformanceByTeamId(teamId = String()) }
    }
}