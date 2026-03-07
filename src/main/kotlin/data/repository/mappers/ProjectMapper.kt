package data.repository.mappers
import data.datasource.model.ProjectRow
import domain.model.Project
import domain.validation.ValidationResult

fun ProjectRow.toDomain():ValidationResult<Project> =
    Project.create(
        id =id,
        name=name,
        assignedTeamId = teamId
    )
