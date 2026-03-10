package domain.usecase
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import domain.model.Mentee
import domain.model.AttendanceState
import domain.usecase.request.*
class GetPoorAttendanceMenteesUseCase (
    private val attendanceRepository: AttendanceRepository,
    private val menteeRepository: MenteeRepository
)  {
    operator fun invoke(minAbsences: RequestminAbsences): List<Mentee> =
        menteeRepository.getAllMentees()
            .filter { mentee -> attendanceRepository.getAttendanceByMenteeId(mentee.id)
                    ?.weeks
                    ?.count {it != AttendanceState.PRESENT }
                    ?.let { it >= minAbsences }
                    ?: false
            }
}