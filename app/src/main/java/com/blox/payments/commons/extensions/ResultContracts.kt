import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun validateOrThrow(value: Boolean, exception: () -> Exception) {
    contract {
        returns() implies value
    }
    if (!value) {
        throw exception()
    }
}
