package com.blox.payments.domain.myscreen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyScreenModel(
    val id: Long? = null,
    val type: ScreenType,
    val headerModel: HeaderModel,
    val body: List<BodyRowModel>,
    val footerModel: FooterModel
) : Parcelable
