package fakeRepository

import data.repository.TeamRepository
import domain.model.Project
import domain.model.Team

class FakeTeamRepository : TeamRepository {
        private val  FackTeamData =listOf(
            Team.create(
                id = "marebits",
                name="Marebits",
                mentorLead = "ibtisam",
                projects = Project.create(
                    id="p08",
                    name="Cybersecurity",
                    //assignedTeamId="123ibt"
                    assignedTeamId="Marebits"
                )
            ),
            Team.create(
                id = "hashira",
               name="Hashira",
               mentorLead = "soad",
                projects = Project.create(
                     //id="p010",
                    id="p10",
                    name="AppGenius",
                   //assignedTeamId="123sod"
                    assignedTeamId="Hashira"
            )
        ),
            Team.create(
                id = "kernels",
                name="Kernels",
                mentorLead = "alaa",
                projects = Project.create(
                    id="p03",
                    name="Motion Matrix",
                    //assignedTeamId="123Tas"
                    assignedTeamId="Kernels"
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