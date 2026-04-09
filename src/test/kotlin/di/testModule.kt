package di
import domain.usecase.*
import data.repository.PerformanceRepository
import data.repository.TeamRepository
import io.mockk.mockk
import org.koin.dsl.module
import fakeRepository.*

class testModule {
    val teamRepoTest=mockk<TeamRepository>()
    val performance=mockk<PerformanceRepository>()

    val repositorytestModule = module {
        single { FakeTeamRepository() }
        single { FakePerformanceRepository() }
    }
    val useCaseTestModule =module {
        single { :: GenerateCrossTeamPreformanceReportUseCase }
    }
}

