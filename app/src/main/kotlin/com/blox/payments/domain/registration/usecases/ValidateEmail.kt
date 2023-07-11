package com.blox.payments.domain.registration.usecases

import androidx.annotation.CheckResult
import com.blox.payments.commons.extensions.resultOf
import javax.inject.Inject
import validateOrThrow

class ValidateEmail @Inject constructor() {

    @CheckResult
    operator fun invoke(email: String?): Result<Boolean> = resultOf {
        validateOrThrow(email?.isNotEmpty() == true) {
            EmailEmptyError
        }

        true
    }
}

sealed interface EmailError
object EmailEmptyError : EmailError, Exception()
