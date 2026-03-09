package domain.model

import domain.validation.validators.*
import domain.validation.ValidationResult

data class Team private constructor(
    val id: String,
    val name: String,
    val mentorLead: String,
    val projects: Project? = null
) {
    constructor(id: String, name: String, mentorLead: String, project: Project) : this(
        id = id,
        name = name,
        mentorLead = mentorLead,
        projects = project
    )

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