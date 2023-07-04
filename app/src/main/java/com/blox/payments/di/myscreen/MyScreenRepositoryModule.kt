package com.blox.payments.di.myscreen

import com.blox.payments.data.myscreen.MyScreenRepositoryImpl
import com.blox.payments.domain.myscreen.MyScreenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MyScreenRepositoryModule {

    @Binds
    abstract fun bindMyScreenRepositoryImpl(
        myScreenRepositoryImpl: MyScreenRepositoryImpl
    ): MyScreenRepository
}
