package data
import data.datasource.model.*
interface EcoSystemDataSource {
    fun getMentees(): Result<List<MenteeRow>>
    fun getMenteeById(id: String): Result<MenteeRow?>
    fun getMenteesByTeamId(teamId: String): Result<List<MenteeRow>>
    fun getTeams(): Result<List<TeamRow>>
    fun getTeamById(teamId: String): Result<TeamRow?>
    fun getPerformances(): Result<List<PerformanceRow>>
    fun getPerformanceByMenteeId(menteeId: String): Result<List<PerformanceRow>>
    fun getProjects(): Result<List<ProjectRow>>
    fun getProjectByTeamId(teamId: String): Result<ProjectRow?>
    fun getAttendances(): Result<List<AttendanceRow>>
    fun getAttendanceByMenteeId(menteeId: String): Result<AttendanceRow?>
}
