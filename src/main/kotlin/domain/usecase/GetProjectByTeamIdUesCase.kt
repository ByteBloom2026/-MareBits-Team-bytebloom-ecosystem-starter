package domain.usecase
import data.repository.TeamRepository
import domain.model.Project
import domain.usecase.request.GetProjectByTeamIdRequest
class GetProjectByTeamIdUesCase(private val teamRepository: TeamRepository) {
    operator fun invoke(request: GetProjectByTeamIdRequest): Result<Project?> {
        return teamRepository.getTeamById(request.teamId).fold(
            onSuccess = { team -> onGetProjectByTeamIdSuccess(team.projects) },
            onFailure = :: onGetProjectByTeamIdFailure
        )
    }
    private fun onGetProjectByTeamIdSuccess(project: Project?): Result<Project?> {
        return Result.success(project)
    }
    private fun onGetProjectByTeamIdFailure(error: Throwable): Result<Project?> {
        return Result.failure(error)
    }
}