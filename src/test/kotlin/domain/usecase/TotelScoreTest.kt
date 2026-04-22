package domain.usecase
import com.google.common.truth.Truth.assertThat
import data.repository.*
import data.repository.TeamRepository
import di_test.testModule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.getValue
import org.junit.jupiter.api.Test

class TotelScoreTest: KoinTest {
    val totalScoreUseCase: TotalScore by inject()
    private val performanceRepository: PerformanceRepository by inject()

    @BeforeEach
    fun setup() {
        startKoin {
            modules(testModule) }
    }
    @AfterEach
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Should calculate total score correctly`() {
        //when
         val result=totalScoreUseCase()
        //Then
        assertThat(result.getOrNull()).isEqualTo(1560.0)
    }
    @Test
    fun `sholde return success result with correct data when repositories succeed`(){
        //When
        val TotelScoreOnSuccess=totalScoreUseCase()
        // Then
        assert(TotelScoreOnSuccess.isSuccess)
    }

//    @Test
//    fun `Should return error result when repositories fail1`() {
//        // When
//        val totalScoreOnFailure = totalScoreUseCase().onFailure {
//            return Result.failure(error)
//
//        }
//        // Then
//        assert(totalScoreOnFailure)
//    }

    @Test
    fun `shold fetch performance data when invoke is called`(){
        //Given
        val performanceRepo=mockk<PerformanceRepository>()
        val teamRepository=mockk<TeamRepository>()
        val totelScpre= TotalScore(performanceRepo
            ,teamRepository)
        every { teamRepository.getAllTeams() } returns Result.success(emptyList())
        every { performanceRepo.getAllPerformance() } returns Result.success(emptyList())
        //When
        totelScpre.invoke()
        //Then
        verify { performanceRepository.getPerformanceByTeamId("team1") }
    }

    @Test
    fun `shold fetch teams data when invoke is called`(){
        //Given
        val performanceRepo=mockk<PerformanceRepository>()
        val teamRepository=mockk<TeamRepository>()
        val totelScpre= TotalScore(performanceRepo
            ,teamRepository)
        every { teamRepository.getAllTeams() } returns Result.success(emptyList())
        every { performanceRepo.getAllPerformance() } returns Result.success(emptyList())
        //When
        totelScpre.invoke()
        //Then
        verify { teamRepository.getAllTeams()}
    }


}