package com.example.myapplication.data.myscreen.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myapplication.domain.myscreen.model.BodyRowModel
import com.example.myapplication.domain.myscreen.model.MyScreenModel
import com.example.myapplication.domain.myscreen.model.ScreenType

@Entity(
    tableName = "MyScreen",
    indices = [Index(value = ["type"], unique = true)]
)
data class MyScreenEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "type") val type: ScreenType,
    @Embedded(prefix = "header_") val header: HeaderEntity?,
    @ColumnInfo(name = "body") val body: List<BodyRowModel>,
    @Embedded(prefix = "footer_") val footer: FooterEntity?
)

fun MyScreenEntity.mapToDomain(): MyScreenModel {
    return with(this) {
        MyScreenModel(
            id = id,
            type = type,
            headerModel = header?.mapToDomain() ?: error("header cannot be null"),
            body = body,
            footerModel = footer?.mapToDomain() ?: error("footer cannot be null"),
        )
    }
}

fun MyScreenModel.mapToCache(): MyScreenEntity {
    return with(this) {
        MyScreenEntity(
            type = type,
            header = headerModel.mapToCache(),
            body = body,
            footer = footerModel.mapToCache()
        )
    }
}
