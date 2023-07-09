package com.blox.payments.di

import com.blox.payments.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val OK_HTTP_CLIENT_TIMEOUT_DEFAULTS = 15_000L

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    internal fun providesLoggingInterceptor(): HttpLoggingInterceptor? = when {
        BuildConfig.DEBUG -> {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        else -> null
    }

    @Singleton
    @Provides
    internal fun providesOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor?
    ): OkHttpClient.Builder = OkHttpClient.Builder().apply {
        connectTimeout(OK_HTTP_CLIENT_TIMEOUT_DEFAULTS, TimeUnit.MILLISECONDS)
        readTimeout(OK_HTTP_CLIENT_TIMEOUT_DEFAULTS, TimeUnit.MILLISECONDS)
        writeTimeout(OK_HTTP_CLIENT_TIMEOUT_DEFAULTS, TimeUnit.MILLISECONDS)
        loggingInterceptor?.also { addInterceptor(it) }
    }

    @Singleton
    @Provides
    internal fun providesOkHttpClient(
        builder: OkHttpClient.Builder
    ): OkHttpClient = with(builder) {
        build()
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}
