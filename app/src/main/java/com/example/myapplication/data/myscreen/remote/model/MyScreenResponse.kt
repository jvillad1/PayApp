package com.example.myapplication.data.myscreen.remote.model

import com.example.myapplication.domain.myscreen.model.MyScreenModel
import com.example.myapplication.domain.myscreen.model.ScreenType
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MyScreenResponse(
    val type: ScreenType,
    val header: HeaderResponse? = null,
    val body: List<BodyRowResponse>? = null,
    val footer: FooterResponse? = null
)

fun MyScreenResponse.mapToDomain(): MyScreenModel = MyScreenModel(
    type = this.type,
    headerModel = header?.mapToDomain() ?: error("header cannot be null"),
    body = body?.map { it.mapToDomain() } ?: error("body cannot be null"),
    footerModel = footer?.mapToDomain() ?: error("footer cannot be null")
)
