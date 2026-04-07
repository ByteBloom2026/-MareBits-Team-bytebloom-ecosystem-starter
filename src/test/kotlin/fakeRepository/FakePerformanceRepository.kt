package fakeRepository
import data.repository.PerformanceRepository
import domain.model.PerformanceSubmission
class FakePerformanceRepository :PerformanceRepository {
    private val performances = listOf(
        PerformanceSubmission.create(
            id = "sub001",
            type = PerformanceSubmission.SubmissionType.TASK,
            score = 90.0,
            menteeId = "m001"
        ),
        PerformanceSubmission.create(
            id = "sub002",
            type = PerformanceSubmission.SubmissionType.TASK,
            score = 95.0,
            menteeId = "m001"
        ),
        PerformanceSubmission.create(
            id = "sub003",
            type = PerformanceSubmission.SubmissionType.TASK,
            score = 80.0,
            menteeId = "m002"
        ),
        PerformanceSubmission.create(
            id = "sub004",
            type = PerformanceSubmission.SubmissionType.TASK,
            score = 85.0,
            menteeId = "m002"
        )
    )
    override fun getAllPerformance(): Result<List<PerformanceSubmission>> {
        return Result.success(performances)
    }
    override fun getPerformanceByMenteeId(menteeId: String): Result<List<PerformanceSubmission>> {
        return Result.success(
            performances.filter { it.menteeId == menteeId }
        )
    }

    override fun getPerformanceByTeamId(teamId: String): Result<List<PerformanceSubmission>> {
        return Result.success(
            performances.filter { it.id==teamId }
        )
    }
}
