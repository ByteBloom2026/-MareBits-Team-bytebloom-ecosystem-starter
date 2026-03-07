package data.datasource

import data.datasource.model.*
import data.EcoSystemDataSource
import data.validator.ColumnsCountValidator
import data.validator.FilePathValidator
import data.validator.LineIsNotEmptyValidator
import data.validator.NoEmptyColumnsValidator
import domain.model.PerformanceSubmission.SubmissionType
import java.io.File

class CsvEcosystemDataSource private constructor(
    private val menteeFile: File, private val teamFile: File, private val performanceFile: File,  private val projectFile: File,  private val attendanceFile: File
) : EcoSystemDataSource {

    private val filePathValidator = FilePathValidator()
    private val lineValidator = LineIsNotEmptyValidator()
    private val noEmptyColumnsValidator = NoEmptyColumnsValidator()

    override fun getMentees(): Result<List<MenteeRow>> {
        return validatedParts(menteeFile, "mentees.csv", 3)
            .map { rows ->
                rows.map { parts ->
                    MenteeRow(
                        parts[0],
                        parts[1],
                        parts[2]
                    )
                }
            }
    }

    override fun getMenteeById(id: String): Result<MenteeRow?> {
        return getMentees().map { mentees ->
            mentees.find { it.id == id }
        }
    }

    override fun getMenteesByTeamId(teamId: String): Result<List<MenteeRow>> {
        return getMentees().map { mentees ->
            mentees.filter { it.teamId == teamId }
        }
    }

    override fun getTeams(): Result<List<TeamRow>> {
        return validatedParts(teamFile, "teams.csv", 3)
            .map { rows ->
                rows.map { parts ->
                    TeamRow(
                        parts[0],
                        parts[1],
                        parts[2]
                    )
                }
            }
    }

    override fun getTeamById(teamId: String): Result<TeamRow?> {
        return getTeams().map { teams ->
            teams.find { it.id == teamId }
        }
    }

    override fun getPerformances(): Result<List<PerformanceRow>> {
        return validatedParts(performanceFile, "performance.csv", 4)
            .map { rows ->
                rows.map { parts ->
                    PerformanceRow(
                        id = parts[0],
                        type = SubmissionType.valueOf(parts[1].uppercase()),
                        score = parts[2].toDoubleOrNull() ?: 0.0,
                        menteeId = parts[3]
                    )
                }
            }
    }
    override fun getPerformanceByMenteeId(
        menteeId: String
    ): Result<List<PerformanceRow>> {
        return getPerformances().map { performances ->
            performances.filter { it.menteeId == menteeId }
        }
    }

    override fun getProjects(): Result<List<ProjectRow>> {
        return validatedParts(projectFile, "projects.csv", 3)
            .map { rows ->
                rows.map { parts ->
                    ProjectRow(
                        parts[0],
                        parts[1],
                        parts[2]
                    )
                }
            }
    }


    override fun getProjectByTeamId(teamId: String): Result<ProjectRow?> {
        return getProjects().map { projects ->
            projects.find { it.teamId == teamId }
        }
    }

    override fun getAttendances(): Result<List<AttendanceRow>> {
        return validatedParts(attendanceFile, "attendance.csv", 4)
            .map { rows ->
                rows.map { parts ->
                    AttendanceRow(
                        menteeId = parts[0],
                        weeks = parts.drop(1).joinToString(",")
                    )
                }
            }
    }

    override fun getAttendanceByMenteeId(
        menteeId: String
    ): Result<AttendanceRow?> {
        return getAttendances().map { attendances ->
            attendances.find { it.menteeId == menteeId }
        }
    }
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

    private fun validateFile(file: File, fileLabel: String): Result<List<String>> {
        val r = filePathValidator.validate(file.path)
        if (r.isFailure) return Result.failure(IllegalArgumentException("$fileLabel: ${r.exceptionOrNull()?.message}"))

        val lines = file.readLines()
        if (lines.size <= 1) return Result.failure(IllegalArgumentException("$fileLabel: No data rows (only header or empty)"))

        return Result.success(lines)
    }

    private fun validateLineToParts(
        line: String,
        fileLabel: String,
        lineNumber: Int,
        expectedColumns: Int
    ): Result<List<String>> {
        val vLine = lineValidator.validate(line)
        if (vLine.isFailure) return Result.failure(IllegalArgumentException("$fileLabel line $lineNumber: ${vLine.exceptionOrNull()?.message}"))

        val parts = line.split(",").map { it.trim() }

        val vCount = ColumnsCountValidator(expectedColumns).validate(parts)
        if (vCount.isFailure) return Result.failure(IllegalArgumentException("$fileLabel line $lineNumber: ${vCount.exceptionOrNull()?.message}"))

        val vEmpty = noEmptyColumnsValidator.validate(parts)
        if (vEmpty.isFailure) return Result.failure(IllegalArgumentException("$fileLabel line $lineNumber: ${vEmpty.exceptionOrNull()?.message}"))

        return Result.success(parts)
    }

    private fun validatedParts(file: File, fileLabel: String, expectedColumns: Int): Result<List<List<String>>> {
        val linesResult = validateFile(file, fileLabel)
        if (linesResult.isFailure) return Result.failure(linesResult.exceptionOrNull()!!)

        val lines = linesResult.getOrNull()!!
        val out = mutableListOf<List<String>>()

        for ((i, line) in lines.drop(1).withIndex()) {
            val lineNumber = i + 2
            val partsResult = validateLineToParts(line, fileLabel, lineNumber, expectedColumns)
            if (partsResult.isFailure) return Result.failure(partsResult.exceptionOrNull()!!)
            out.add(partsResult.getOrNull()!!)
        }

        return Result.success(out)
    }
}//