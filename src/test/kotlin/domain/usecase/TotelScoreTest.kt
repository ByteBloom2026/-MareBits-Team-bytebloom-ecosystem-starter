package domain.usecase
import com.google.common.truth.Truth.assertThat
import data.repository.*
import data.repository.TeamRepository
import di_test.testModule
import domain.model.*
import domain.usecase.request.GenerateTeamAttendanceReportRequest
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.getValue
//import kotlin.test.Test
import fakeRepository.*
import fakeRepository.FakePerformanceRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TotelScoreTest: KoinTest {
    val totalScoreUseCase: TotalScore by inject()
    private val performanceRepository: PerformanceRepository by inject()
    private val TeamRepository: TeamRepository by inject()
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
    fun `shold fetch performance data when invoke is called`(){
        //When
        totalScoreUseCase.invoke()
        //Then
        verify { performanceRepository.getPerformanceByTeamId("Soa123") }
    }
    @Test
    fun `shold fetch teams data when invoke is called`(){
        //When
        totalScoreUseCase.invoke()
        //Then
        verify { TeamRepository.getTeamById("Ibt123")}
    }
    @Test
    fun `sholde return success result with correct data when repositories succeed`(){
        //When
        val TotelScoreOnSuccess=totalScoreUseCase()
        // Then
        assert(TotelScoreOnSuccess.isSuccess)
    }


    @Test
    fun `Should return error result when repositories fail1`() {
        // When
        val totalScoreOnFailure = totalScoreUseCase()

        // Then
        assert(totalScoreOnFailure.isFailure)
    }






}