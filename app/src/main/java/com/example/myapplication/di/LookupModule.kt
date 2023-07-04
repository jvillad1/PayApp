package com.example.myapplication.di

import com.example.myapplication.commons.resources.StringLookup
import com.example.myapplication.commons.resources.StringLookupImpl
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
