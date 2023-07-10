package com.blox.payments.domain.login.usecases

import androidx.annotation.CheckResult
import com.blox.payments.commons.extensions.resultOf
import javax.inject.Inject
import validateOrThrow

class Login @Inject constructor() {

    @CheckResult
    operator fun invoke(email: String?, password: String?): Result<Boolean> = resultOf {
        validateOrThrow(email?.isNotEmpty() == true) {
            EmailEmptyError
        }

        validateOrThrow(password?.isNotEmpty() == true) {
            PasswordEmptyError
        }

        true
    }
}

sealed interface LoginError
object EmailEmptyError : LoginError, Exception()
object PasswordEmptyError : LoginError, Exception()
