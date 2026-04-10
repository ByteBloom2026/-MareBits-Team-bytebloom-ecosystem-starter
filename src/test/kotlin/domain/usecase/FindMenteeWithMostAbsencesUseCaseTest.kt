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
        fun `findMenteeWithMostAbsencesShouldReturnCorrectMentee`() {
            // Action
            val result = findMenteeWithMostAbsencesUseCase()

            // Assertion
            val output = result.getOrNull()

            assertTrue(result.isSuccess)
            assertNotNull(output)
            assertEquals("m002", output?.first?.id)
            assertEquals(2, output?.second)
        }
    }

