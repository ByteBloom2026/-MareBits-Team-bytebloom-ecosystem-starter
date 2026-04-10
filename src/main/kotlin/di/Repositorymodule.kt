package di

import data.repository.AttendanceRepository
import data.repository.AttendanceRepositoryImpl
import data.repository.MenteeRepository
import data.repository.MenteeRepositoryImpl
import data.repository.PerformanceRepository
import data.repository.PerformanceRepositoryImpl
import data.repository.ProjectRepository
import data.repository.ProjectRepositoryImpl
import data.repository.TeamRepository
import data.repository.TeamRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.dsl.bind

val Repositorymodule = module {

    singleOf(::AttendanceRepositoryImpl) bind AttendanceRepository :: class
    singleOf(::MenteeRepositoryImpl) bind MenteeRepository :: class
    singleOf(::PerformanceRepositoryImpl) bind  PerformanceRepository ::class
    singleOf(::ProjectRepositoryImpl) bind ProjectRepository :: class
    singleOf(::TeamRepositoryImpl) bind  TeamRepository::class

}//