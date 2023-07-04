package com.blox.payments.data.myscreen.cache

import com.blox.payments.commons.extensions.resultOf
import com.blox.payments.data.myscreen.cache.model.mapToCache
import com.blox.payments.data.myscreen.cache.model.mapToDomain
import com.blox.payments.domain.myscreen.model.MyScreenModel
import javax.inject.Inject

class MyScreenCacheDataSource @Inject constructor(
    private val myScreenDao: MyScreenDao
) {

    suspend fun storeMyScreen(myScreenModel: MyScreenModel): Result<Long> = resultOf {
        myScreenDao.insertScreen(myScreenModel.mapToCache())
    }

    suspend fun getMyScreen(rowId: Long): Result<MyScreenModel> = resultOf {
        myScreenDao.getScreenByRowId(rowId)?.mapToDomain()
            ?: error("error getting MyScreen from cache")
    }
}
