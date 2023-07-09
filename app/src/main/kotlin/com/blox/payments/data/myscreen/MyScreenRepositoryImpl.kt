package com.blox.payments.data.myscreen

import com.blox.payments.commons.extensions.mapResult
import com.blox.payments.data.myscreen.cache.MyScreenCacheDataSource
import com.blox.payments.data.myscreen.remote.MyScreenRemoteDataSource
import com.blox.payments.domain.myscreen.MyScreenRepository
import com.blox.payments.domain.myscreen.model.MyScreenModel
import timber.log.Timber
import javax.inject.Inject

class MyScreenRepositoryImpl @Inject constructor(
    private val myScreenCacheDataSource: MyScreenCacheDataSource,
    private val myScreenRemoteDataSource: MyScreenRemoteDataSource
) : MyScreenRepository {

    override suspend fun fetchMyScreen(): Long =
        myScreenRemoteDataSource
            .getMyScreen()
            .mapResult { myScreenModel ->
                Timber.d("Persist the result from remote in cache")

                myScreenCacheDataSource
                    .storeMyScreen(myScreenModel)
                    .getOrThrow()
            }
            .getOrThrow()

    override suspend fun getMyScreen(myScreenIdentifier: Long): MyScreenModel =
        myScreenCacheDataSource.getMyScreen(myScreenIdentifier).getOrThrow()
}
