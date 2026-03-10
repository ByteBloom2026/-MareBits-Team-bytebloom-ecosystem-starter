package domain.usecase
import data.repository.MenteeRepository
import data.repository.ProjectRepository
import domain.usecase.request.RequestMenteeId

class GetNumberOfProjectsByMenteeIdUseCase(
    private val menteeRepository: MenteeRepository,
    private val projectsRepository: ProjectRepository
) {
    operator fun invoke(menteeId: RequestMenteeId): Int {
        val mentee = menteeRepository.getAllMentees()
            .find { it.id == menteeId }
            ?: return 0
        val teamId = mentee.teamId
        return projectsRepository.getAllProjects()
            .count { it.assignedTeamId == teamId }
    }
}