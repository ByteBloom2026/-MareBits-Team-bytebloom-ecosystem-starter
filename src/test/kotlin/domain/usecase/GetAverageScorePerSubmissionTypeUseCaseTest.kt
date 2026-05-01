package domain.usecase;
import com.google.common.truth.Truth.assertThat
import di_test.testModule
import domain.model.PerformanceSubmission.SubmissionType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
class GetAverageScorePerSubmissionTypeUseCaseTest : KoinTest {
    private val useCase : GetAverageScorePerSubmissionTypeUseCase by inject ()
    @BeforeEach
    fun setup(){
        startKoin { modules(testModule)}
    }
    @AfterEach
    fun tearDown(){
        stopKoin()
    }
    @Test
    fun `should calculate averages for all submission types`() {
        //When
        val result = useCase().getOrNull()
        //Then
        assertThat(result?.get(SubmissionType.TASK)).isWithin(0.01).of(87.5)
    }
    @Test
    fun `should return average for book club`() {
        //When
        val result = useCase().getOrNull()
        //Then
        assertThat(result?.get(SubmissionType.BOOK_CLUB)).isWithin(0.01).of(85.0)
    }
}