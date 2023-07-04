package com.blox.payments.data.myscreen.remote.model

import com.blox.payments.domain.myscreen.model.BodyRowModel
import com.blox.payments.domain.myscreen.model.BodyRowType
import com.blox.payments.domain.myscreen.model.CrossSellingModel

data class CrossSellingResponse(
    val text: String
) : BodyRowResponse {

    override val type: BodyRowType = BodyRowType.CROSS_SELLING

    override fun mapToDomain(): BodyRowModel = CrossSellingModel(
        text = text
    )
}
