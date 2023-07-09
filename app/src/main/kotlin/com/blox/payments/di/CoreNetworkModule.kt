package com.blox.payments.di

import com.blox.payments.BuildConfig
import com.blox.payments.data.myscreen.remote.model.BodyRowResponse
import com.blox.payments.data.myscreen.remote.model.CrossSellingResponse
import com.blox.payments.data.myscreen.remote.model.MessageResponse
import com.blox.payments.data.myscreen.remote.model.PaymentMethodInfoResponse
import com.blox.payments.data.myscreen.remote.model.SectionResponse
import com.blox.payments.domain.myscreen.model.BodyRowType
import com.blox.payments.domain.myscreen.model.ScreenType
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
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
        .add(
            ScreenType::class.java,
            EnumJsonAdapter.create(ScreenType::class.java).withUnknownFallback(
                ScreenType.PROCESSING
            )
        )
        .add(
            BodyRowType::class.java,
            EnumJsonAdapter.create(BodyRowType::class.java).withUnknownFallback(null)
        )
        .add(
            PolymorphicJsonAdapterFactory.of(
                BodyRowResponse::class.java,
                "type"
            ).withSubtype(
                CrossSellingResponse::class.java,
                BodyRowType.CROSS_SELLING.name
            ).withSubtype(
                MessageResponse::class.java,
                BodyRowType.MESSAGE.name
            ).withSubtype(
                PaymentMethodInfoResponse::class.java,
                BodyRowType.PAYMENT_METHOD_INFO.name
            ).withSubtype(
                SectionResponse::class.java,
                BodyRowType.SECTION.name
            )
        )
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
