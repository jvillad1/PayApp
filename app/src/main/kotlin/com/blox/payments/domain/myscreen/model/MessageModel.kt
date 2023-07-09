package com.blox.payments.domain.myscreen.model

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MessageModel(
    val text: String
) : BodyRowModel {

    @IgnoredOnParcel
    override val type: BodyRowType = BodyRowType.MESSAGE
}
