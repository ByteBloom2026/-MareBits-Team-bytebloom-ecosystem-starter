package domain.usecase
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import domain.model.Mentee
import domain.model.AttendanceState
class GetPerfectAttendanceMenteesUseCase (
    private val attendanceRepository: AttendanceRepository,
    private val menteeRepository: MenteeRepository
)
{
    operator fun invoke(): Result<List<Mentee>> {
         return menteeRepository.getAllMentees()
             .fold(
                 onSuccess = ::GetPerfectAttendanceMentewsSuccess,
                 onFailure = ::GetPerfectAttendanceMentewsFailure
             )
    }
    private fun GetPerfectAttendanceMentewsSuccess(mentees: List<Mentee>):Result<List<Mentee>>{
        val preferencesAttendanceMentees =mentees.filter { mentee ->
            val attendance = attendanceRepository.getAttendanceByMenteeId(mentee.id).getOrNull()
            attendance?.
            weeks?.
            all { it == AttendanceState.PRESENT }
                ?: false
        }
        return Result.success(preferencesAttendanceMentees)

    }
    private fun  GetPerfectAttendanceMentewsFailure(error: Throwable):Result<List<Mentee>>{
        return Result.failure(error)
    }
}




