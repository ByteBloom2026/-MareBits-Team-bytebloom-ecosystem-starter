package domain.usecase

import com.google.common.truth.Truth.assertThat
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.model.Attendance
import domain.model.AttendanceState
import domain.model.Mentee
import domain.model.Team
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class GetAverageAttendancePercentagePerTeamUseCaseTest {
    private val teamRepository = mockk<TeamRepository>()
    private val menteeRepository = mockk<MenteeRepository>()
    private val attendanceRepository = mockk<AttendanceRepository>()
    private val useCase = GetAverageAttendancePercentagePerTeamUseCase(teamRepository, menteeRepository, attendanceRepository)

    @Test
    fun `should calculate average attendance for team`(){
        //Given
        val teams = listOf(
            Team.create("team1", "marebits", "Mentor1", null),
            Team.create("team2", "power", "Mentor2", null)
        )
        every { teamRepository.getAllTeams() } returns Result.success(teams)
        val mentees = listOf(
            Mentee.create("m111", "Kenan", "team1"),
            Mentee.create("m222", "Salma", "team2")
        )
        mentees.forEach { mentee ->
            every { menteeRepository.getMenteesByTeamId(mentee.teamId) } returns Result.success(listOf(mentee))
        }

        val attendances= listOf(
            Attendance.create("a111", listOf(AttendanceState.PRESENT, AttendanceState.PRESENT)),
            Attendance.create("a222", listOf(AttendanceState.PRESENT, AttendanceState.ABSENT))
        )
        attendances.forEach { attendance ->
            every { attendanceRepository.getAttendanceByMenteeId(attendance.menteeId) } returns Result.success(attendance) }
        //When
        val result = useCase()
        //Then
        assertThat(result.isSuccess).isTrue()
        val breakdown = result.getOrNull()
        assertThat(breakdown?.get("marebits")).isWithin(0.01).of(100.0)
        assertThat(breakdown?.get("power")).isWithin(0.01).of(50.0)
    }
}