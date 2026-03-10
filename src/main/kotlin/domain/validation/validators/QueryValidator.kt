package domain.validation.validators
import domain.model.exception.*
import domain.validation.EcosystemValidator

class QueryValidator : EcosystemValidator<String> {
    private val minLength: Int = 2
    override fun validate(data: String): String {
        val value = data.trim()
        if (value.isEmpty()) {
            throw  EmptyQueryException()
        }
        if (value.length < minLength) {
            throw InvalidQueryException()
        }
        return value
    }
}


