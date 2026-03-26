import java.io.File
import data.datasource.CsvEcosystemDataSource
import domain.usecase.*
import data.repository.*
import domain.model.Team
import domain.model.exception.SearchTeamException
import org.koin.core.context.startKoin
import di.Repositorymodule
import di.useCaseModule


fun main() {
   startKoin {
       val appModule={
           Repositorymodule
           useCaseModule
       }
   }


















    val csvDataSource = CsvEcosystemDataSource.getInstance(
        File("src/main/resources/mentees.csv"),
        File("src/main/resources/teams.csv"),
        File("src/main/resources/performance.csv"),
        File("src/main/resources/projects.csv"),
        File("src/main/resources/attendance.csv")
    )
    val menteeRepository = MenteeRepositoryImpl(csvDataSource)
    val teamRepositry = TeamRepositoryImpl(csvDataSource)
    val performanceRepository = PerformanceRepositoryImpl(csvDataSource)
    val projectRepository = ProjectRepositoryImpl(csvDataSource)
    val attendanceRepository = AttendanceRepositoryImpl(csvDataSource)

    val searchTeamsByName = SearchTeamsByNameUseCase(teamRepositry)
    try {
        searchTeamsByName
    } catch () {

    }
    val searchTeamsByNameResult = searchTeamsByName("   ")

    searchTeamsByNameResult.fold(
        onSuccess = ::onSearchTeamsByNameSuccess,
        onFailure = ::onSearchTeamsByNameFailure
    )

}

private fun onSearchTeamsByNameSuccess(teams: List<Team>) {

}
private fun onSearchTeamsByNameFailure(throwable: Throwable) {
    val searchTeamException = throwable as SearchTeamException
    when(searchTeamException){
        else -> {}
    }

}