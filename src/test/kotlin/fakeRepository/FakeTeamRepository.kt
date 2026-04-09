package fakeRepository

import data.repository.TeamRepository
import domain.model.Project
import domain.model.Team

class FakeTeamRepository(id: String,
                         name: String
                         , mentorLead: String,
                          projects: Project) : TeamRepository {
        private val  FackTeamData =listOf(
            Team.create(
                id = "123ibt",
                name="ScentOfSecurity",
                mentorLead = "ibtisam",
                projects = Project.create(
                    id="p08",
                    name="Cybersecurity",
                    assignedTeamId="123ibt"
                )
            ),
            Team.create(
                id = "123sod",
               name="NullPointers",
               mentorLead = "soad",
                projects = Project.create(
                     id="p010",
                    name="AppGenius",
                   assignedTeamId="123sod"
            )
        ),
            Team.create(
                id = "123Tas",
                name="FrameMasters",
                mentorLead = "Tasname",
                projects = Project.create(
                    id="p03",
                    name="Motion Matrix",
                    assignedTeamId="123Tas"
                )
            )
        )

    override fun getAllTeams(): Result<List<Team>> {
        return Result.success(FackTeamData)
    }

    override fun getTeamById(teamId: String): Result<Team?> {
        return Result.success(FackTeamData.find { it.id==teamId })
    }

    override fun getMentorLeadByTeamId(teamId: String): Result<String?> {
        val Team= FackTeamData.find { it.id==teamId }
        return Result.success(Team?.mentorLead)
    }

    override fun searchTeamsByName(keyword: String): Result<List<Team>> {
        return Result.success(FackTeamData)
    }
}