package data.datasource

import data.datasource.model.*
import data.EcoSystemDataSource
import data.datasource.model.exception.FileIsEmptyException
import data.validator.ColumnsCountValidator
import data.validator.FilePathValidator
import data.validator.LineIsNotEmptyValidator
import data.validator.NoEmptyColumnsValidator
import domain.model.PerformanceSubmission.SubmissionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class CsvEcosystemDataSource(
    private val menteeFile: File, private val teamFile: File, private val performanceFile: File,  private val projectFile: File,  private val attendanceFile: File
) : EcoSystemDataSource {
    private val filePathValidator = FilePathValidator()
    private val lineValidator = LineIsNotEmptyValidator()
    private val noEmptyColumnsValidator = NoEmptyColumnsValidator()

    override suspend fun getMentees(): Result<List<MenteeRow>> {
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
    override suspend fun getMenteeById(id: String): Result<MenteeRow?> {
        return getMentees().map { mentees ->
            mentees.find { it.id == id }
        }
    }
    override suspend fun getMenteesByTeamId(teamId: String): Result<List<MenteeRow>> {
        return getMentees().map { mentees ->
            mentees.filter { it.teamId == teamId }
        }
    }

    override suspend fun getTeams(): Result<List<TeamRow>> {
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
    override suspend fun getTeamById(teamId: String): Result<TeamRow?> {
        return getTeams().map { teams ->
            teams.find { it.id == teamId }
        }
    }
    override suspend fun getPerformances(): Result<List<PerformanceRow>> {
        return validatedParts(performanceFile, "performance.csv", 4)
            .map { rows ->
                rows.map { parts ->
                    PerformanceRow(
                        id = parts[1],
                        type = SubmissionType.valueOf(parts[2].uppercase()),
                        score = parts[3].toDoubleOrNull() ?: 0.0,
                        menteeId = parts[0]
                    )
                }
            }
    }
    override suspend fun getPerformanceByMenteeId(
        menteeId: String
    ): Result<List<PerformanceRow>> {
        return getPerformances().map { performances ->
            performances.filter { it.menteeId == menteeId }
        }
    }

    override suspend fun getPerformanceByTeamId(
        teamId: String
    ): Result<List<PerformanceRow>> {
        return getPerformances().map { performances ->
            performances.filter { it.id==teamId}
        }
    }

    override suspend fun getProjects(): Result<List<ProjectRow>> {
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
    override suspend fun getProjectByTeamId(teamId: String): Result<ProjectRow?> {
        return getProjects().map { projects ->
            projects.find { it.teamId == teamId }
        }
    }
    override suspend fun getAttendances(): Result<List<AttendanceRow>> {
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
    override suspend fun getAttendanceByMenteeId(
        menteeId: String
    ): Result<AttendanceRow?> {
        return getAttendances().map { attendances ->
            attendances.find { it.menteeId == menteeId }
        }
    }
    private suspend fun validateFile(file: File, fileLabel: String): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            try {
                filePathValidator.validate(file.path)
                val fileLines = file.readLines()
                if (fileLines.size <= 1) {
                    Result.failure(FileIsEmptyException())
                } else {
                    Result.success(fileLines)
                }
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
    }
    private fun validateLineToParts(
        line: String,
        fileLabel: String,
        lineNumber: Int,
        expectedColumns: Int
    ): Result<List<String>> {
        return try {
            lineValidator.validate(line)
            val columns = line.split(",").map { column -> column.trim() }
            ColumnsCountValidator(expectedColumns).validate(columns)
            noEmptyColumnsValidator.validate(columns)
            Result.success(columns)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
    private suspend fun validatedParts(file: File, fileLabel: String, expectedColumns: Int): Result<List<List<String>>> {
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
}