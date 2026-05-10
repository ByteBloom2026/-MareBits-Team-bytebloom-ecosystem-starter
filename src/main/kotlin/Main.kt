import kotlinx.coroutines.runBlocking
import domain.usecase.GetMenteeNamesByTeamNameUseCase
import data.repository.*
import data.datasource.CsvEcosystemDataSource
import domain.usecase.request.GetMenteeNamesByTeamNameRequest
import java.io.File
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun main ()=runBlocking {
    val csvDataSource = CsvEcosystemDataSource(
        File("src/main/resources/mentees.csv"),
        File("src/main/resources/teams.csv"),
        File("src/main/resources/performance.csv"),
        File("src/main/resources/projects.csv"),
        File("src/main/resources/attendance.csv")
    )
    val menterepo = MenteeRepositoryImpl(csvDataSource)
    val Teamrepo = TeamRepositoryImpl(csvDataSource)
    val getMenteeNamesByTeamNameUseCase =
        GetMenteeNamesByTeamNameUseCase(Teamrepo, menterepo)
    val getMenteeNamesByTeamNameRequest = GetMenteeNamesByTeamNameRequest(teamName = " ")


    getMenteeNamesByTeamNameUseCase
        .invoke(getMenteeNamesByTeamNameRequest)
        .onSuccess { namesFlow ->
            namesFlow
                .onEach { name ->
                }
                .launchIn(this)

        }


}




