package com.blox.payments.domain.registration.usecases

import androidx.annotation.CheckResult
import com.blox.payments.commons.extensions.resultOf
import javax.inject.Inject
import validateOrThrow

class ValidateCountry @Inject constructor() {

    @CheckResult
    operator fun invoke(country: String?): Result<Boolean> = resultOf {
        validateOrThrow(country?.isNotEmpty() == true) {
            CountryEmptyError
        }

        true
    }
}

sealed interface CountryError
object CountryEmptyError : CountryError, Exception()
