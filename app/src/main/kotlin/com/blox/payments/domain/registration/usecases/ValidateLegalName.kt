package com.blox.payments.domain.registration.usecases

import androidx.annotation.CheckResult
import com.blox.payments.commons.extensions.resultOf
import javax.inject.Inject
import validateOrThrow

class ValidateLegalName @Inject constructor() {

    @CheckResult
    operator fun invoke(firstName: String?, lastName: String?): Result<Boolean> = resultOf {
        validateOrThrow(firstName?.isNotEmpty() == true) {
            FirstNameEmptyError
        }

        validateOrThrow(lastName?.isNotEmpty() == true) {
            LastNameEmptyError
        }

        true
    }
}

sealed interface LegalNameError
object FirstNameEmptyError : LegalNameError, Exception()
object LastNameEmptyError : LegalNameError, Exception()
