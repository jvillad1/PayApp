package com.blox.payments.domain.myscreen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeaderModel(
    val title: String,
    val iconUrl: String?,
//    val closeAction: Action
) : Parcelable
