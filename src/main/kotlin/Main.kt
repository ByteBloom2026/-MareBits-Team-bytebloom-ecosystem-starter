import java.io.File
import data.datasource.CsvEcosystemDataSource
import domain.usecase.*
import data.repository.*
import domain.model.Team
import domain.model.exception.InvalidTeamNameLengthException
import domain.model.exception.SearchTeamException

fun main() {
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


    val groupTeamsByMentorUseCase = GroupTeamsByMentorUseCase(teamRepositry)
    println(" Teams Grouped By Mentor : ${groupTeamsByMentorUseCase}")

    val getMenteeNameById = GetMenteeNameByIdUseCase(menteeRepository)
    println("Mentee name (M1): ${getMenteeNameById("M1")}")

    val getMenteeNamesByTeamName = GetMenteeNamesByTeamNameUseCase(teamRepositry, menteeRepository)
    println("Mentees in team Alpha: ${getMenteeNamesByTeamName("Alpha")}")

    val getNumberOfProjectsByMenteeId = GetNumberOfProjectsByMenteeIdUseCase(menteeRepository, projectRepository)
    println("Projects count for mentee M1: ${getNumberOfProjectsByMenteeId("M1")}")

    val getTeamByMenteeName = GetTeamByMenteeNameUseCase(teamRepositry, menteeRepository)
    println("Team of mentee Alice: ${getTeamByMenteeName("Alice")}")

    val isMenteeInTeam = IsMenteeInTeamUseCase(teamRepositry, menteeRepository)
    println("Is mentee M1 in team Alpha? ${isMenteeInTeam("M1", "Alpha")}")

    val generateTeamAttendanceReport = GenerateTeamAttendanceReportUseCase(attendanceRepository, menteeRepository)
    println("Attendance report for team T1: ${generateTeamAttendanceReport("T1")}")

    val getAverageAttendancePercentagePerTeam =
        GetAverageAttendancePercentagePerTeamUseCase(teamRepositry, menteeRepository, attendanceRepository)
    println("Average attendance per team: ${getAverageAttendancePercentagePerTeam()}")

    val getMostAbsentMentees = GetMostAbsentMenteesUseCase(menteeRepository, attendanceRepository)
    println("Most absent mentees: ${getMostAbsentMentees()}")

    val getPerfectAttendanceMentees = GetPerfectAttendanceMenteesUseCase(attendanceRepository, menteeRepository)
    println("Perfect attendance mentees: ${getPerfectAttendanceMentees()}")

    val getPoorAttendanceMentees = GetPoorAttendanceMenteesUseCase(attendanceRepository, menteeRepository)
    println("Poor attendance mentees (>=3 absences): ${getPoorAttendanceMentees(3)}")

    val getAverageScorePerSubmissionType = GetAverageScorePerSubmissionTypeUseCase(performanceRepository)
    println("Average score per submission type: ${getAverageScorePerSubmissionType()}")

    val getMenteePerformanceBreakdown = GetMenteePerformanceBreakdownUseCase(performanceRepository)
    println("Performance breakdown for mentee M1: ${getMenteePerformanceBreakdown("M1")}")

    val getMenteesWithLowAverageScore = GetMenteesWithLowAverageScoreUseCase(menteeRepository, performanceRepository)
    println("Low average score mentees (<60): ${getMenteesWithLowAverageScore(60.0)}")

    val getTeamAverageScore = GetTeamAverageScoreUseCase(menteeRepository, performanceRepository)
    println("Team T1 average score: ${getTeamAverageScore("T1")}")

    val getTopScoringMentee = GetTopScoringMenteeUseCase(menteeRepository, performanceRepository)
    println("Top scoring mentee: ${getTopScoringMentee()}")
}

private fun onSearchTeamsByNameSuccess(teams: List<Team>) {

}

private fun onSearchTeamsByNameFailure(throwable: Throwable) {
    val searchTeamException = throwable as SearchTeamException
    when(searchTeamException){
        else -> {}
    }
}