package com.blox.payments.domain.myscreen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BodyRowType : Parcelable {
    CROSS_SELLING,
    MESSAGE,
    PAYMENT_METHOD_INFO,
    SECTION
}
