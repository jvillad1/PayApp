package com.blox.payments.data.myscreen.cache.model

import androidx.room.ColumnInfo
import com.blox.payments.domain.myscreen.model.HeaderModel

data class HeaderEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "iconUrl") val iconUrl: String?
)

fun HeaderEntity.mapToDomain(): HeaderModel {
    return with(this) {
        HeaderModel(
            title = title,
            iconUrl = iconUrl
        )
    }
}

fun HeaderModel.mapToCache(): HeaderEntity {
    return with(this) {
        HeaderEntity(
            title = title,
            iconUrl = iconUrl
        )
    }
}
