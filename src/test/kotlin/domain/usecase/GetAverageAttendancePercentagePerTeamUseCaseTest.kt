package domain.usecase

import com.google.common.truth.Truth.assertThat
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import domain.model.Attendance
import domain.model.AttendanceState
import domain.model.Mentee
import domain.usecase.request.GetProjectByTeamIdRequest
import io.mockk.every
import io.mockk.mockk
import org.junit.runner.Request
import kotlin.test.Test

class GetAverageAttendancePercentagePerTeamUseCaseTest {
    private val attendanceRepository = mockk<AttendanceRepository>()
    private val menteeRepository = mockk<MenteeRepository>()
    private val useCase = GetAverageAttendancePercentagePerTeamUseCase(attendanceRepository, menteeRepository)

    @Test
    fun `should calculate average attendance percentage for a team correctly`() {
        // Given
        val teamId = "team_A"
        val mentees = listOf(
            Mentee.create("m111", "Ali", teamId),
            Mentee.create("m222", "Mona", teamId)
        )

        every { menteeRepository.getMenteesByTeamId(teamId) } returns Result.success(mentees)

        every { attendanceRepository.getAttendanceByMenteeId("m111") } returns Result.success(
            Attendance.create("m111", listOf(AttendanceState.ABSENT, AttendanceState.ABSENT, AttendanceState.PRESENT))
        )

        every { attendanceRepository.getAttendanceByMenteeId("m222") } returns Result.success(
            Attendance.create("m222", listOf(AttendanceState.PRESENT, AttendanceState.PRESENT))
        )

        // When
        val request = null
        val result = useCase(request)

        val expectedPercentage = 75.0

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(expectedPercentage)
        @Test
        fun `should return failure when team has no mentees`() {
            // Given
            val teamId = "empty_team"
            val request = GetProjectByTeamIdRequest(teamId)

            every { menteeRepository.getMenteesByTeamId(teamId) } returns Result.success(emptyList())

            // When
            val result = useCase(request)

            // Then
            assertThat(result.isSuccess).isTrue()
            assertThat(result.getOrNull()).isEqualTo(0.0)
        }
    }
}
