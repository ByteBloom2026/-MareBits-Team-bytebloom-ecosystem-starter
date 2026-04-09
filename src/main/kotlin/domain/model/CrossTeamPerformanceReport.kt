package domain.model

data class CrossTeamPerformanceReport(
    val ScoreAndTeamName: List<TeamScore>,
    val Repotr: TeamScore?
)

data class TeamScore(
    val Score : Double,
    val TeamName :String
)