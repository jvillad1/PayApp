package com.blox.payments.data.myscreen.remote

import com.blox.payments.data.myscreen.remote.model.MyScreenResponse
import retrofit2.Response
import retrofit2.http.GET

interface MyScreenApi {

    @GET("7499db13-295d-426f-a34d-b31240859e3e")
    suspend fun getMyScreen(): Response<MyScreenResponse>
}
