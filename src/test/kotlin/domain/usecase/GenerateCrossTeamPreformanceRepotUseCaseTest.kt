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


class GenerateCrossTeamPreformanceRepotUseCaseTest : KoinTest {
    private val myTestModule = testModule()
    val generateCrossTeamPreformanceReportUseCase: GenerateCrossTeamPreformanceReportUseCase by inject()

    @BeforeEach
    fun start() {

        startKoin {
            modules(
                myTestModule.useCaseTestModule, myTestModule.repositorytestModule
            )
        }
    }

    @AfterEach
    fun stop() {
        stopKoin()
    }

    @Test
    fun `should return success result with correct data when repositories succeed`() {
        //given
        val repo = myTestModule.teamRepoTest
        val perf = myTestModule.performance

        val fakeTeams = listOf(
            Team(id = "1", name = "Team Alpha"),
            Team(id = "2", name = "Team Beta")
        )
        every { repo.getAllTeams() } returns Result.success(fakeTeams)
        every { perf.getPerformanceByTeamId(any()) } returns Result.success(emptyList())

        //  When
        val result = generateCrossTeamPreformanceReportUseCase
            .invoke(request = GenerateTeamAttendanceReportRequest(teamId = String()))

        //  Then
        assert(result.isSuccess)
    }


    @Test
    fun `should fetch teams and performance data when invoke is called`() {
        //givan
        val TeamRepoTest = myTestModule.teamRepoTest
        val PerformanceTest = myTestModule.performance
        //when
        generateCrossTeamPreformanceReportUseCase
            .invoke(request = GenerateTeamAttendanceReportRequest(teamId = String()))
        //then
        verify { TeamRepoTest.getAllTeams() }
        verify { PerformanceTest.getAllPerformance() }
    }
}