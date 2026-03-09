package domain.validation
interface EcosystemValidator<T>{
    fun validate(data: T): Result<String>
}