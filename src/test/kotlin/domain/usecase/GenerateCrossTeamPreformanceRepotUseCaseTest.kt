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
    fun `sholde return success result with correct data when repositories succeed`(){
        //When
        val generateCrossTeamPreformanceReportUseCaseOnSuccess=generateCrossTeamPreformanceReportUseCase
            .invoke(request = GenerateTeamAttendanceReportRequest(teamId = String()))
        //Then
        assert(generateCrossTeamPreformanceReportUseCaseOnSuccess.isSuccess)
    }
    @Test
    fun `Should return error result when repositories fail`(){
        //When
        val generateCrossTeamPreformanceReportUseCaseOnFailure=generateCrossTeamPreformanceReportUseCase
            .invoke(request = GenerateTeamAttendanceReportRequest(teamId = String()))
        //Then
        assert(generateCrossTeamPreformanceReportUseCaseOnFailure.isFailure)
    }
    @Test
    fun `should fetch teams and performance data when invoke is called`() {
        generateCrossTeamPreformanceReportUseCase
            .invoke(request = GenerateTeamAttendanceReportRequest(teamId = String()))
        verify { teamRepo.getTeamById(teamId = String()) }
        verify { perfRepo.getPerformanceByTeamId(teamId = String()) }
    }
    @Test
    fun ``(){
        //Given
        //When
        //Then
    }
    @Test
    fun ``(){
        //Given
        //When
        //Then
    }
}