package com.blox.payments.domain.myscreen.usecases

import androidx.annotation.CheckResult
import com.blox.payments.commons.extensions.resultOf
import com.blox.payments.domain.myscreen.MyScreenRepository
import com.blox.payments.domain.myscreen.model.MyScreenModel
import javax.inject.Inject

class GetMyScreen @Inject constructor(
    private val myScreenRepository: MyScreenRepository
) {

    @CheckResult
    suspend operator fun invoke(myScreenIdentifier: Long): Result<MyScreenModel> = resultOf {
        val fetchResult = myScreenRepository.getMyScreen(myScreenIdentifier)

        fetchResult
    }
}
