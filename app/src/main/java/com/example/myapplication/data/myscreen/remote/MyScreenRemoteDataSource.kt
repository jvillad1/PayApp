package com.example.myapplication.data.myscreen.remote

import com.example.myapplication.commons.extensions.resultOf
import com.example.myapplication.data.myscreen.remote.model.mapToDomain
import com.example.myapplication.domain.myscreen.model.MyScreenModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MyScreenRemoteDataSource @Inject constructor(
    private val myScreenApi: MyScreenApi
) {

    suspend fun getMyScreen(): Result<MyScreenModel> = resultOf {
        val response = withContext(Dispatchers.IO) {
            myScreenApi.getMyScreen()
        }

        val body = response.body()

        if (response.isSuccessful && body != null) {
            body.mapToDomain()
        } else {
            Timber.d("The retrieved response is not successful and/or body is empty")
            error("The retrieved response is not successful and/or body is empty")
        }
    }
}
