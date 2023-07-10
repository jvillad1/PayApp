package com.blox.payments.domain.registration.usecases

import androidx.annotation.CheckResult
import com.blox.payments.commons.extensions.resultOf
import javax.inject.Inject
import validateOrThrow

class ValidatePhoneNumber @Inject constructor() {

    @CheckResult
    operator fun invoke(birthDate: String?): Result<Boolean> = resultOf {
        validateOrThrow(birthDate?.isNotEmpty() == true) {
            PhoneNumberEmptyError
        }

        true
    }
}

sealed interface PhoneNumberError
object PhoneNumberEmptyError : PhoneNumberError, Exception()
