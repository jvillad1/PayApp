package com.example.myapplication.data.myscreen.cache

import com.example.myapplication.commons.extensions.resultOf
import com.example.myapplication.data.myscreen.cache.model.mapToCache
import com.example.myapplication.data.myscreen.cache.model.mapToDomain
import com.example.myapplication.domain.myscreen.model.MyScreenModel
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
