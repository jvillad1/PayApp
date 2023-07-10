package com.blox.payments.domain.registration.usecases

import androidx.annotation.CheckResult
import com.blox.payments.commons.extensions.resultOf
import javax.inject.Inject
import timber.log.Timber

class WithRefCode @Inject constructor() {

    @CheckResult
    operator fun invoke(refCode: String?): Result<Boolean> = resultOf {
        if (!refCode.isNullOrEmpty()) {
            Timber.d("Persist the Ref code")
        }

        true
    }
}
