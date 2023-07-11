package com.blox.payments.domain.registration.usecases

import androidx.annotation.CheckResult
import com.blox.payments.commons.extensions.resultOf
import javax.inject.Inject
import validateOrThrow

class ValidatePassword @Inject constructor() {

    @CheckResult
    operator fun invoke(password: String?): Result<Boolean> = resultOf {
        validateOrThrow(password?.isNotEmpty() == true) {
            PasswordEmptyError
        }

        true
    }
}

sealed interface PasswordError
object PasswordEmptyError : PasswordError, Exception()
