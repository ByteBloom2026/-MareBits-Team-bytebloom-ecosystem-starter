package di

import data.EcoSystemDataSource
import data.datasource.CsvEcosystemDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File

val dataSourceModule = module {

    single(named("menteeFile")) { File("src/main/resources/mentees.csv") }
    single(named("teamFile")) { File("src/main/resources/teams.csv") }
    single(named("performanceFile")) { File("src/main/resources/performance.csv") }
    single(named("projectFile")) { File("src/main/resources/projects.csv") }
    single(named("attendanceFile")) { File("src/main/resources/attendance.csv") }

    single<EcoSystemDataSource> {
        CsvEcosystemDataSource(
            menteeFile = get(named("menteeFile")),
            teamFile = get(named("teamFile")),
            performanceFile = get(named("performanceFile")),
            projectFile = get(named("projectFile")),
            attendanceFile = get(named("attendanceFile"))
        )
    }
}