package com.example.myapplication.domain.myscreen.model

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentMethodInfoModel(
    val imageUrl: String,
    val amountPaid: Double,
    val discount: String? = null,
    val rawAmount: Double,
    val methodType: String
) : BodyRowModel {

    @IgnoredOnParcel
    override val type: BodyRowType = BodyRowType.PAYMENT_METHOD_INFO
}
