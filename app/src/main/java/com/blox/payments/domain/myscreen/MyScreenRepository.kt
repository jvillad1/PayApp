package com.blox.payments.domain.myscreen

import com.blox.payments.domain.myscreen.model.MyScreenModel

interface MyScreenRepository {

    suspend fun fetchMyScreen(): Long

    suspend fun getMyScreen(myScreenIdentifier: Long): MyScreenModel
}
