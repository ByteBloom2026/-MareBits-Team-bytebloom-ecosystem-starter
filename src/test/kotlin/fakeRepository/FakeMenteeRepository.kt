package fakeRepository

import data.repository.MenteeRepository
import domain.model.Mentee

class FakeMenteeRepository : MenteeRepository {

        private val mentees = listOf(
            Mentee.create("m001", "Ahmad", "123ibt"),
            Mentee.create("m002", "Sara", "123Tas"),
            Mentee.create("m003", "Ali", "123sod")
        )

        override fun getAllMentees(): Result<List<Mentee>> {
            return Result.success(mentees)
        }

        override fun getMenteeById(id: String): Result<Mentee?> {
            return Result.success(mentees.find { it.id == id })
        }

        override fun getMenteesByTeamId(teamId: String): Result<List<Mentee>> {
            return Result.success(mentees.filter { it.teamId == teamId })
        }
    }
