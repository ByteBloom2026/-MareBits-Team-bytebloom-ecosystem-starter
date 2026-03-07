package data.repository.mappers
import data.datasource.model.MenteeRow
import domain.model.Mentee
import domain.validation.ValidationResult

fun MenteeRow.toDomain(): ValidationResult<Mentee> =
    Mentee.create(id = id
        , name = name
        , teamId = teamId)