package com.example.myapplication.domain.myscreen

import com.example.myapplication.domain.myscreen.model.MyScreenModel

interface MyScreenRepository {

    suspend fun fetchMyScreen(): Long

    suspend fun getMyScreen(myScreenIdentifier: Long): MyScreenModel
}
