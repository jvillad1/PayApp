package com.example.myapplication.data.converters

import androidx.room.TypeConverter
import com.example.myapplication.data.myscreen.cache.model.ButtonEntity
import com.example.myapplication.domain.myscreen.model.BodyRowModel
import com.example.myapplication.domain.myscreen.model.BodyRowType
import com.example.myapplication.domain.myscreen.model.CrossSellingModel
import com.example.myapplication.domain.myscreen.model.MessageModel
import com.example.myapplication.domain.myscreen.model.NestedBodyModel
import com.example.myapplication.domain.myscreen.model.PaymentMethodInfoModel
import com.example.myapplication.domain.myscreen.model.ScreenType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

class MyScreenCacheConverters {

    @TypeConverter
    fun toScreenType(value: String): ScreenType = enumValueOf(value)

    @TypeConverter
    fun fromBodyRowType(value: BodyRowType) = value.name

    @TypeConverter
    fun toBodyRowType(value: String): BodyRowType = enumValueOf(value)

    @TypeConverter
    fun fromScreenType(value: ScreenType) = value.name

    private val bodyRowModelParametrizedType: ParameterizedType =
        Types.newParameterizedType(List::class.java, BodyRowModel::class.java)
    private val bodyRowModelJsonAdapter: JsonAdapter<List<BodyRowModel>> =
        Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory.of(BodyRowModel::class.java, "type")
                    .withSubtype(CrossSellingModel::class.java, BodyRowType.CROSS_SELLING.name)
                    .withSubtype(MessageModel::class.java, BodyRowType.MESSAGE.name)
                    .withSubtype(PaymentMethodInfoModel::class.java, BodyRowType.PAYMENT_METHOD_INFO.name)
                    .withSubtype(NestedBodyModel::class.java, BodyRowType.SECTION.name)
            )
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(bodyRowModelParametrizedType)

    @TypeConverter
    fun toBodyRowList(value: String?): List<BodyRowModel>? {
        return if (value != null) bodyRowModelJsonAdapter.fromJson(value) else null
    }

    @TypeConverter
    fun fromBodyRow(bodyRowModelList: List<BodyRowModel>?): String? {
        return bodyRowModelJsonAdapter.toJson(bodyRowModelList)
    }

    private val buttonParametrizedType: ParameterizedType =
        Types.newParameterizedType(List::class.java, ButtonEntity::class.java)
    private val buttonJsonAdapter: JsonAdapter<List<ButtonEntity>> =
        Moshi.Builder().build().adapter(buttonParametrizedType)

    @TypeConverter
    fun toButtonList(value: String?): List<ButtonEntity>? {
        return if (value != null) buttonJsonAdapter.fromJson(value) else null
    }

    @TypeConverter
    fun fromButtonList(buttonList: List<ButtonEntity>?): String? {
        return buttonJsonAdapter.toJson(buttonList)
    }
}
