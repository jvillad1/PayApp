package com.blox.payments.data.myscreen.remote.model

import com.blox.payments.domain.myscreen.model.BodyRowModel
import com.blox.payments.domain.myscreen.model.BodyRowType
import com.blox.payments.domain.myscreen.model.PaymentMethodInfoModel

data class PaymentMethodInfoResponse(
    val methodType: String
) : BodyRowResponse {

    override val type: BodyRowType = BodyRowType.PAYMENT_METHOD_INFO

    override fun mapToDomain(): BodyRowModel = PaymentMethodInfoModel(
        imageUrl = "",
        amountPaid = 100.0,
        discount = "50% OFF",
        rawAmount = 100.0,
        methodType = methodType
    )
}
