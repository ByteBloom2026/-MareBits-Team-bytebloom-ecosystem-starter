package data.repository.mappers
import data.datasource.model.MenteeRow
import domain.model.Mentee


fun MenteeRow.toDomain(): Mentee =
    Mentee.create(id = id
        , name = name
        , teamId = teamId)