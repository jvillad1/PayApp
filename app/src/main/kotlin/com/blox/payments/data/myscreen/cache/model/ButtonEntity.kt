package com.blox.payments.data.myscreen.cache.model

import androidx.room.ColumnInfo
import com.blox.payments.domain.myscreen.model.ButtonModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ButtonEntity(
    @ColumnInfo(name = "label") val label: String
)

fun ButtonEntity.mapToDomain(): ButtonModel {
    return with(this) {
        ButtonModel(
            label = label
        )
    }
}

fun ButtonModel.mapToCache(): ButtonEntity {
    return with(this) {
        ButtonEntity(
            label = label
        )
    }
}
