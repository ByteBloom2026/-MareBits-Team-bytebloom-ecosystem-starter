import java.io.File
import data.datasource.CsvEcosystemDataSource
import domain.usecase.*
import data.repository.*
import domain.model.Team
import domain.usecase.request.GetMenteeNameByIdRequest
import domain.usecase.request.SearchTeamsByNameRequest

fun main() {

    val csvDataSource = CsvEcosystemDataSource.getInstance(
        File("src/main/resources/mentees.csv"),
        File("src/main/resources/teams.csv"),
        File("src/main/resources/performance.csv"),
        File("src/main/resources/projects.csv"),
        File("src/main/resources/attendance.csv")
    )

    val menteeRepository = MenteeRepositoryImpl(csvDataSource)
    val teamRepository = TeamRepositoryImpl(csvDataSource)

    val searchTeamsByNameUseCase = SearchTeamsByNameUseCase(teamRepository)

    val searchTeamsByNameResult = searchTeamsByNameUseCase(SearchTeamsByNameRequest("Alpha"))

    searchTeamsByNameResult.fold(
        onSuccess = ::onSearchTeamsByNameSuccess,
        onFailure = ::onFailure
    )

    val getMenteeNameByIdUseCase = GetMenteeNameByIdUseCase(menteeRepository)

    val getMenteeNameResult = getMenteeNameByIdUseCase(
        GetMenteeNameByIdRequest("M1")
    )

    getMenteeNameResult.fold(
        onSuccess = ::onGetMenteeNameSuccess,
        onFailure = ::onFailure
    )
}

private fun onSearchTeamsByNameSuccess(teams: List<Team>) {
    println("Found: $teams")
}

private fun onGetMenteeNameSuccess(name: String?) {
    println("Found: $name")
}

private fun onFailure(error: Throwable) {
    println("Access Denied: ${error.message}")
}