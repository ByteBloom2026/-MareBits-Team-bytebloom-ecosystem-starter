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
        return menteeRepository.getAllMentees()
            .fold(
            onSuccess = ::GetMostAbsentMenteesSuccess,
            onFailure = ::GetMostAbsentMenteesFailure
            )
    }
    private fun GetMostAbsentMenteesSuccess(mentees: List<Mentee>): Result<List<Pair<Mentee, Int>>> {
        val absentList = mentees.mapNotNull { mentee ->
            attendanceRepository.getAttendanceByMenteeId(mentee.id).getOrNull()?.weeks?.let { weeks ->
                val absentCount = weeks.count { it != AttendanceState.PRESENT }
                mentee to absentCount
            }
        }.sortedByDescending { it.second }
        return Result.success(absentList)
    }
   private fun GetMostAbsentMenteesFailure(error: Throwable):  Result<List<Pair<Mentee, Int>>>{
       return Result.failure(error)
   }


}

