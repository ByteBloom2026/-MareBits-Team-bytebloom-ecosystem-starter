package domain.usecase
import com.google.common.truth.Truth.assertThat
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import domain.model.Attendance
import domain.model.AttendanceState
import domain.model.Mentee
import domain.usecase.request.GenerateTeamAttendanceReportRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GenerateTeamAttendanceReportUseCaseTest {
    private val attendanceRepository = mockk<AttendanceRepository>()
    private val menteeRepository = mockk<MenteeRepository>()
    private val useCase = GenerateTeamAttendanceReportUseCase(attendanceRepository, menteeRepository)
    @Test
    fun `should calculate absence counts correctly for all mentees in a team`() {
        //Given
        val teamId = "ByteBloom"
        val mentees = listOf(Mentee.create("m111", "Kenan", teamId),
            Mentee.create("m222", "Salma", teamId))

        every { menteeRepository.getMenteesByTeamId(teamId) } returns Result.success(mentees)

        every { attendanceRepository.getAttendanceByMenteeId("m111") } returns Result.success(
            Attendance.create("m111", listOf(AttendanceState.ABSENT, AttendanceState.ABSENT, AttendanceState.PRESENT)))

        every { attendanceRepository.getAttendanceByMenteeId("m222") } returns Result.success(
            Attendance.create("m222", listOf(AttendanceState.PRESENT, AttendanceState.PRESENT)))
        //When
        val result = useCase(GenerateTeamAttendanceReportRequest(teamId))
        //Then
        val expectedReport = mapOf("Kenan" to 2, "Salma" to 0)

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(expectedReport)


        }

    }
