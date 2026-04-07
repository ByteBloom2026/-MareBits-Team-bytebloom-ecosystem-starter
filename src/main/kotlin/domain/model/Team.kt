package domain.model

import domain.validation.validators.*
data class Team private constructor(
    val id: String,
    val name: String,
    val mentorLead: String,
    val projects: Project? = null
) {


    companion object {
        val teamNameValidator = TeamNameValidator()
        fun create(id: String, name: String, mentorLead: String, projects: Project?): Team {
            val teamName = teamNameValidator.validate(name)
            return Team(
                id = id,
                name = teamName,
                mentorLead = mentorLead,
                projects = projects
            )
        }
    }

}