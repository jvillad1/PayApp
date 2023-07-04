package com.blox.payments.domain.myscreen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface BodyRowModel : Parcelable {
    val type: BodyRowType
}
