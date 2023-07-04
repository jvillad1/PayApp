package com.example.myapplication.di.myscreen

import com.example.myapplication.data.myscreen.remote.MyScreenApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object MyScreenNetworkModule {

    @Provides
    internal fun providesMyApplicationApi(retrofit: Retrofit): MyScreenApi =
        retrofit.create(MyScreenApi::class.java)
}
