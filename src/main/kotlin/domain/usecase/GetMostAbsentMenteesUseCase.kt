package domain.usecase
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import domain.model.Mentee
import domain.model.AttendanceState
class GetMostAbsentMenteesUseCase (
    private val menteeRepository: MenteeRepository,
    private val attendanceRepository: AttendanceRepository
){
    operator fun invoke(): Result<List<Pair<Mentee, Int>>> {
        val absentList = menteeRepository.getAllMentees()
            .mapNotNull { mentee ->
                attendanceRepository.getAttendanceByMenteeId(mentee.id)
                    ?.weeks
                    ?.let { weeks ->
                        val absentCount = weeks.count { it != AttendanceState.PRESENT}
                        mentee to absentCount
                    }
            }
            .sortedByDescending { it.second }
        return Result.success(absentList)
    }
}
