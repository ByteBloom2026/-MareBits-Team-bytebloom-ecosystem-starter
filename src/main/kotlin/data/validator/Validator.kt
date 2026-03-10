package data.validator

interface Validator<T> {
    fun validate(input: T): T
}