package di
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import domain.usecase.*
val UseCaseTestModule  = module {
    singleOf(::CountTeamsByMentorUseCase)
    singleOf(::FindTeamsWithNoProjectUseCase)
    singleOf(::GenerateTeamAttendanceReportUseCase)
    singleOf(::GetAverageAttendancePercentagePerTeamUseCase)
    singleOf(::GetAverageScorePerSubmissionTypeUseCase)
    singleOf(::GetMenteeNameByIdUseCase)
    singleOf(::GetMenteeNamesByTeamNameUseCase)
    singleOf(::GetMenteePerformanceBreakdownUseCase)
    singleOf(::GetMenteesWithLowAverageScoreUseCase)
    singleOf(::GetMostAbsentMenteesUseCase)
    singleOf(::GetNumberOfProjectsByMenteeIdUseCase)
    singleOf(::GetPerfectAttendanceMenteesUseCase)
    singleOf(::GetPoorAttendanceMenteesUseCase)
    singleOf(::GetProjectByTeamIdUesCase)
    singleOf(::GetTeamAverageScoreUseCase)
    singleOf(::GetTeamByMenteeNameUseCase)
    singleOf(::GetTopScoringMenteeUseCase)
    singleOf(::GroupTeamsByMentorUseCase)
    singleOf(::IsMenteeInTeamUseCase)
    singleOf(::SearchTeamsByNameUseCase)
    singleOf(::CalculateOverallAttendancePercentageUseCase)
    singleOf(::GetTopPerformingMenteesBySubmissionTypeUseCase)
    singleOf(::TotalScore)
}