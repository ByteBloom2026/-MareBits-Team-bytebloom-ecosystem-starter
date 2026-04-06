package di
import domain.usecase.*
import data.repository.PerformanceRepository
import data.repository.TeamRepository
import io.mockk.mockk
import org.koin.dsl.module

class testModule {
    val teamRepoTest=mockk<TeamRepository>()
    val performance=mockk<PerformanceRepository>()

    val repositorytestModule = module {
        single { teamRepoTest }
        single { Performance }
    }
    val useCaseTestModule =module {
        single { :: GenerateCrossTeamPreformanceReportUseCase }
    }
}

