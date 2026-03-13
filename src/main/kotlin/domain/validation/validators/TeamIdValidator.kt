package domain.validation.validators
import domain.model.exception.*
import domain.validation.EcosystemValidator


class TeamIdValidator : EcosystemValidator<String> {
    override fun validate(data: String): String {
        val value = data.trim()
        if (value.isEmpty()) {
            throw SearchTeamException.EmptyTeamIdExcpection()
        }
        if (!value.first().isLetter()) {
            throw NotCapitalizedNameException()
        }
        if (!value.all { it.isLetter() }) {
            throw NameContainsInvalidCharactersException()
        }
        return  value.lowercase()
    }
}