package domain.usecase
import data.repository.MenteeRepository
import data.repository.ProjectRepository
import domain.model.Mentee
import domain.usecase.request.GetMenteeNameByIdRequest
class GetNumberOfProjectsByMenteeIdUseCase(
    private val menteeRepository: MenteeRepository,
    private val projectsRepository: ProjectRepository
) {
    operator fun invoke(request: GetMenteeNameByIdRequest): Result<Int> {
        return menteeRepository.getAllMentees().fold(
            onSuccess = {mentees -> onGetProjectsNumberByMenteeIdSuccess(mentees, request.menteeId)},
            onFailure = ::onGetProjectsNumberByMenteeIdFailure
        )
    }
    private fun onGetProjectsNumberByMenteeIdSuccess(mentees: List<Mentee>, menteeId: String): Result<Int> {
        val mentee = mentees.find { it.id == menteeId } ?: return 0
        val teamId = mentee.teamId
        val count = projectsRepository.getAllProjects()
            .count { it.assignedTeamId == teamId }
        return Result.success(count)
    }
    private fun onGetProjectsNumberByMenteeIdFailure(error: Throwable): Result<Int>{
        return Result.failure(error)
    }
}