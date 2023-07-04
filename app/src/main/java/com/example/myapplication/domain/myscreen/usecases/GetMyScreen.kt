package com.example.myapplication.domain.myscreen.usecases

import androidx.annotation.CheckResult
import com.example.myapplication.commons.extensions.resultOf
import com.example.myapplication.domain.myscreen.MyScreenRepository
import com.example.myapplication.domain.myscreen.model.MyScreenModel
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
