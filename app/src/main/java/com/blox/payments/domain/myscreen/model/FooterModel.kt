package com.blox.payments.domain.myscreen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FooterModel(
    val buttonModelList: List<ButtonModel>
) : Parcelable
