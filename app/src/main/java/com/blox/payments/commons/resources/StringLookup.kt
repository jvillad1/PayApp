package com.blox.payments.commons.resources

import androidx.annotation.ArrayRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface StringLookup {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String

    fun getQuantityString(@PluralsRes res: Int, quantity: Int): String

    fun getQuantityString(
        @PluralsRes res: Int,
        quantity: Int,
        vararg formatArgs: Any
    ): String

    fun getStringArray(@ArrayRes resId: Int): Array<String>
}
