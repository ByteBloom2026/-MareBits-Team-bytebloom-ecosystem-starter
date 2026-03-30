package domain.usecase

import fakeRepository.FakeAttendanceRepository
import fakeRepository.FakeMenteeRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class FindMenteeWithMostAbsencesUseCaseTest {

        @Test
        fun findMenteeWithMostAbsencesShouldReturnCorrectMentee() {

            // Setup
            val menteeRepository = FakeMenteeRepository()
            val attendanceRepository = FakeAttendanceRepository()

            val useCase = FindMenteeWithMostAbsencesUseCase(
                menteeRepository,
                attendanceRepository
            )

            // Action
            val result = useCase()

            // Assertion
            val output = result.getOrNull()

            assertTrue(result.isSuccess)
            assertNotNull(output)
            assertEquals("m002", output?.first?.id)
            assertEquals(2, output?.second)
        }
    }

