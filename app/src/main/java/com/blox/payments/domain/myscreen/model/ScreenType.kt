package com.blox.payments.domain.myscreen.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Suppress("MagicNumber")
@Parcelize
enum class ScreenType : Parcelable {
    APPROVED,
    PROCESSING,
    REJECTED;

    fun toColor(): Color = when (this) {
        APPROVED -> Color(0xFF00A650)
        PROCESSING -> Color(0xFFFF7733)
        REJECTED -> Color(0xFFF23D4F)
    }
}
