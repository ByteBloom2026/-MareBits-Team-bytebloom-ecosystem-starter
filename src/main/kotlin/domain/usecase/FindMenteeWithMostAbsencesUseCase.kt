package domain.usecase

import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import domain.model.AttendanceState
import domain.model.Mentee

class FindMenteeWithMostAbsencesUseCase (
    private val menteeRepository: MenteeRepository,
    private val attendanceRepository: AttendanceRepository
){
    operator fun invoke(): Result<Pair<Mentee, Int>?> {
        return menteeRepository.getAllMentees().fold(
            onSuccess = ::onFindMenteeWithMostAbsencesSuccess,
            onFailure = ::onFindMenteeWithMostAbsencesFailure
        )
    }

    private fun onFindMenteeWithMostAbsencesSuccess(
        mentees: List<Mentee>
    ): Result<Pair<Mentee, Int>?> {
        val mostAbsentMentee = mentees.map { mentee ->
            val absenceCount = attendanceRepository.getAttendanceByMenteeId(mentee.id)
                .getOrNull()
                ?.weeks
                ?.count { it == AttendanceState.ABSENT }
                ?: 0

            mentee to absenceCount
        }.maxByOrNull { it.second }

        return Result.success(mostAbsentMentee)
    }

    private fun onFindMenteeWithMostAbsencesFailure(
        error: Throwable
    ): Result<Pair<Mentee, Int>?> {
        return Result.failure(error)
    }
}