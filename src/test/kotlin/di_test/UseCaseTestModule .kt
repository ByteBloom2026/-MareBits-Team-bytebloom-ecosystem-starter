package di_test
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import domain.usecase.*
val UseCaseTestModule  = module {
    singleOf(::GenerateTeamAttendanceReportUseCase)
    singleOf(::GetAverageAttendancePercentagePerTeamUseCase)
    singleOf(::GetMenteeNameByIdUseCase)
    singleOf(::GetMenteePerformanceBreakdownUseCase)
    singleOf(::CalculateOverallAttendancePercentageUseCase)
    singleOf(::GetTopPerformingMenteesBySubmissionTypeUseCase)
    singleOf(::TotalScore)
    singleOf(::FindMenteeWithMostAbsencesUseCase)
}