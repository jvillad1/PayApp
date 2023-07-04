package com.blox.payments.di

import com.blox.payments.commons.resources.StringLookup
import com.blox.payments.commons.resources.StringLookupImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LookupModule {

    @Binds
    internal abstract fun bindsStringLookup(stringLookupImpl: StringLookupImpl): StringLookup
}
