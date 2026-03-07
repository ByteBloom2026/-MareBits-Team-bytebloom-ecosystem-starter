package data.repository.mappers
import data.datasource.model.TeamRow
import domain.model.Team
import domain.model.Project
import domain.validation.ValidationResult
fun TeamRow.toDomain(project: Project? = null): ValidationResult<Team> =
    Team.create(
        id = id,
        name = name,
        mentorLead = mentorLead,
        projects = project
    )
