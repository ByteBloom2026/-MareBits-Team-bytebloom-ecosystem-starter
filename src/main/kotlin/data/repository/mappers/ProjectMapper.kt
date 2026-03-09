package data.repository.mappers
import data.datasource.model.ProjectRow
import domain.model.Project
fun ProjectRow.toDomain():Project =
    Project.create(
        id =id,
        name=name,
        assignedTeamId = teamId
    )
