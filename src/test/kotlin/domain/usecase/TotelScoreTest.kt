package domain.usecase
import com.google.common.truth.Truth.assertThat
import data.repository.*
import data.repository.TeamRepository
import di_test.testModule
import domain.model.*
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.getValue
import kotlin.test.Test
import fakeRepository.*

class TotelScoreTest: KoinTest {
    val TotalScore: TotalScore by inject()
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
        //given
        val result=FakePerformanceRepository().getAllPerformance()
        //when
          TotalScore.invoke()
        //Then
        assertThat(result.getOrNull()).isEqualTo(520.0)
    }
    @Test
    fun `shold fetch performance data when invoke is called`(){
        //When
        TotalScore.invoke()
        //Then
        verify { performanceRepository.getPerformanceByTeamId("Soa123") }
    }
    @Test
    fun `shold fetch teams data when invoke is called`(){
        //When
        TotalScore.invoke()
        //Then
        verify { TeamRepository.getTeamById("Ibt123")}
    }
    @Test
    fun `sholde return success result with correct data when repositories succeed`(){
        //When
        val TotelScoreOnSuccess=TotalScore.invoke()
        // Then
        assert(TotelScoreOnSuccess.isSuccess)
    }
    @Test
    fun `Should return error result when repositories fail`(){
        //When
        val TotelScoreOnFailure=TotalScore.invoke()
        //Then
        assert(TotelScoreOnFailure.isFailure)
    }
    @Test
    fun `Should handle negative scores gracefully`() {
        // When
        val result = TotalScore.invoke()
        // Then
        assert(result.isFailure)
    }
//


}