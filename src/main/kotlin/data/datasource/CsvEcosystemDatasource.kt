package data.datasource

import data.datasource.model.*
import domain.*
import data.EcoSystemDataSource
import domain.model.PerformanceSubmission.SubmissionType
import java.io.File

class CsvEcosystemDataSource private constructor(
    menteeFile: File, teamFile: File, performanceFile: File, projectFile: File, attendanceFile: File
) : EcoSystemDataSource {
    private val menteeLines = menteeFile.readLines().drop(1)
    private val teamLines = teamFile.readLines().drop(1)
    private val performanceLines = performanceFile.readLines().drop(1)
    private val projectLines = projectFile.readLines().drop(1)
    private val attendanceLines = attendanceFile.readLines().drop(1)

    override fun getMentees(): Result<List<MenteeRow>> = menteeLines.map {
        val parts = it.split(",")
        MenteeRow(parts[0].trim(), parts[1].trim(), parts[2].trim())
    }

    override fun getMenteeById(id: String):
            MenteeRow? = getMentees().find { it.id == id }

    override fun getMenteesByTeamId(teamId: String):
            List<MenteeRow> = getMentees().filter { it.teamId == teamId }

    override fun getTeams():
            List<TeamRow> = teamLines.map {
        val parts = it.split(",")
        TeamRow(
            parts[0].trim(), parts[1].trim(),
            parts[2].trim()
        )
    }

    override fun getTeamById(teamId: String):
            TeamRow? = getTeams().find { it.id == teamId }

    override fun getPerformances():
            List<PerformanceRow> = performanceLines.map {
        val parts = it.split(",")
        PerformanceRow(
            parts[0].trim(), SubmissionType.valueOf(parts[1].trim().uppercase()),
            parts[2].trim().toDoubleOrNull() ?: 0.0,
            parts[3].trim()
        )
    }

    override fun getPerformanceByMenteeId(menteeId: String): List<PerformanceRow> =
        getPerformances().filter { it.menteeId == menteeId }

    override fun getProjects():
            List<ProjectRow> = projectLines.map {
        val parts = it.split(",")
        ProjectRow(
            parts[0].trim(), parts[1].trim(),
            parts[2].trim()
        )
    }

    override fun getProjectByTeamId(teamId: String):
            ProjectRow? = getProjects().find { it.teamId == teamId }

    override fun getAttendances():
            List<AttendanceRow> = attendanceLines.map {
        val parts = it.split(",")
        AttendanceRow(
            menteeId = parts[0].trim(),
            weeks = parts.drop(1).joinToString(",")
        )
    }

    override fun getAttendanceByMenteeId(menteeId: String):
            AttendanceRow? =
        getAttendances().find { it.menteeId == menteeId }

    companion object {
        @Volatile
        private var instance: CsvEcosystemDataSource? = null

        fun getInstance(
            menteeFile: File,
            teamFile: File,
            performanceFile: File,
            projectFile: File,
            attendanceFile: File
        ):
                CsvEcosystemDataSource {
            return instance ?: synchronized(this) {
                instance ?: CsvEcosystemDataSource(
                    menteeFile,
                    teamFile,
                    performanceFile,
                    projectFile,
                    attendanceFile
                ).also { instance = it }
            }
        }
    }
}//