package domain.usecase
import data.repository.MenteeRepository
import data.repository.TeamRepository
import domain.model.Team
import domain.usecase.request.GetMenteeNamesByTeamNameRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import  kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach


class GetMenteeNamesByTeamNameUseCase(
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
) {
    suspend operator fun invoke(request: GetMenteeNamesByTeamNameRequest): Result<Flow<String>> {
        return teamRepository.searchTeamsByName(request.teamName).fold(
            onSuccess = { teams ->
                val result = GetMenteeNamesByTeamNameSuccess(teams)
                result.map { namesFlow ->
                    namesFlow
                        .debounce(500)
                        .filter { name -> name.isNotBlank() && name.length > 3 }
                        .distinctUntilChanged()
                        .onEach { println(it) }
                }
            },
            onFailure = { error ->
                onGetMenteeNameByTeamNameFailure(error)}
        )
    }
    private suspend fun GetMenteeNamesByTeamNameSuccess(teams: Flow<Team>): Result<Flow<String>> {
        val targetTeam = teams.firstOrNull()
        val allMentees = menteeRepository.getAllMentees().getOrDefault(emptyList())
        val names = allMentees
            .filter { it.teamId == targetTeam?.id }
            .map { it.name }
        return Result.success(names.asFlow())
    }
    private fun onGetMenteeNameByTeamNameFailure(error: Throwable): Result<Flow<String>> {
        return Result.failure(error)
    }
}