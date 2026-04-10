package di
import data.repository.AttendanceRepository
import data.repository.MenteeRepository
import data.repository.PerformanceRepository
import data.repository.TeamRepository
import fakeRepository.FakeAttendanceRepository
import fakeRepository.FakeMenteeRepository
import fakeRepository.FakePerformanceRepository
import fakeRepository.FakeTeamRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
val repositoryTestModule = module{
    singleOf(::FakeAttendanceRepository) bind AttendanceRepository::class
    singleOf(::FakeMenteeRepository) bind MenteeRepository::class
    singleOf(::FakePerformanceRepository) bind PerformanceRepository::class
    singleOf(::FakeTeamRepository) bind TeamRepository::class
}