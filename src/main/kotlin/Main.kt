import java.io.File
import data.datasource.CsvEcosystemDataSource
import domain.usecase.*
import data.repository.*
import domain.model.Team
import domain.usecase.request.GetMenteeNameByIdRequest
import domain.usecase.request.GetMenteeNamesByTeamNameRequest
import domain.usecase.request.SearchTeamsByNameRequest
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
   val   teamFile = File("src/main/resources/teams.csv")
    val  menteeFile = File("src/main/resources/mentees.csv")
    val teamRepository = TeamRepositoryImpl(teamFile)
    val menteeRepository = MenteeRepositoryImpl(menteeFile)

    val getMenteeNamesByTeamNameUseCase=
        GetMenteeNamesByTeamNameUseCase(teamRepository,menteeRepository)
        .invoke(GetMenteeNamesByTeamNameRequest(""))


    getMenteeNamesByTeamNameUseCase

}



