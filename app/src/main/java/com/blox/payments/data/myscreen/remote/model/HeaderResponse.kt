package com.blox.payments.data.myscreen.remote.model

import com.blox.payments.domain.myscreen.model.HeaderModel

const val DEFAULT_ICON_URL =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/64px-Bitcoin.svg.png"

data class HeaderResponse(
    val title: String? = null,
    val iconUrl: String? = null,
//    val closeAction: Action
)

fun HeaderResponse.mapToDomain(): HeaderModel = HeaderModel(
    title = this.title ?: error("title cannot be null"),
    iconUrl = this.iconUrl ?: DEFAULT_ICON_URL
)
