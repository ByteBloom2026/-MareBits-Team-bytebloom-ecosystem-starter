package domain.usecase

import di_test.testModule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject


class FindMenteeWithMostAbsencesUseCaseTest: KoinTest {
    private val findMenteeWithMostAbsencesUseCase : FindMenteeWithMostAbsencesUseCase by inject()
    @BeforeEach
    fun setUp() {
        startKoin { modules(testModule) }
    }
    @AfterEach
    fun tearDown() { stopKoin() }

        @Test
        fun `should return successful result when finding mentee with most absences`() {
            //when
            val result = findMenteeWithMostAbsencesUseCase()
            //then
            assertTrue(result.isSuccess)
        }
        @Test
        fun `should return non null output when mentees and attendance data are available`(){
            //when
            val result = findMenteeWithMostAbsencesUseCase()
            //then
            assertNotNull(result)
        }
        @Test
        fun `should return mentee with id m002 as the mentee with most absences`(){
            val result = findMenteeWithMostAbsencesUseCase()
            val returnedMenteeId = result.getOrNull()?.first?.id
            assertEquals("m002", returnedMenteeId)
        }
        @Test
        fun `should return absence count of 2 for the mentee with most absences`(){
            val result = findMenteeWithMostAbsencesUseCase()
            val returnedAbsenceCount = result.getOrNull()?.second
            assertEquals(2,returnedAbsenceCount)
        }
    }

