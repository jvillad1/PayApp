package com.blox.payments.data.myscreen.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blox.payments.data.converters.MyScreenCacheConverters
import com.blox.payments.data.myscreen.cache.model.MyScreenEntity

@Database(
    entities = [
        MyScreenEntity::class
    ],
    version = 1
)
@TypeConverters(MyScreenCacheConverters::class)
abstract class MyScreenDatabase : RoomDatabase() {

    abstract fun myScreenDao(): MyScreenDao
}
