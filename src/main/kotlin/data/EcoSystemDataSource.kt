package data
import data.datasource.model.*
interface EcoSystemDataSource {
    suspend fun getMentees(): Result<List<MenteeRow>>
    suspend fun getMenteeById(id: String): Result<MenteeRow?>
    suspend fun getMenteesByTeamId(teamId: String): Result<List<MenteeRow>>
    suspend fun getTeams(): Result<List<TeamRow>>
    suspend fun getTeamById(teamId: String): Result<TeamRow?>
    suspend fun getPerformances(): Result<List<PerformanceRow>>
    suspend fun getPerformanceByMenteeId(menteeId: String): Result<List<PerformanceRow>>
    suspend fun getProjects(): Result<List<ProjectRow>>
    suspend fun getProjectByTeamId(teamId: String): Result<ProjectRow?>
    suspend fun getAttendances(): Result<List<AttendanceRow>>
    suspend fun getAttendanceByMenteeId(menteeId: String): Result<AttendanceRow?>
    suspend fun getPerformanceByTeamId(teamId: String): Result<List<PerformanceRow>>
}
