package com.blox.payments.data.myscreen.remote.model

import com.blox.payments.domain.myscreen.model.BodyRowModel
import com.blox.payments.domain.myscreen.model.BodyRowType
import com.blox.payments.domain.myscreen.model.MessageModel

data class MessageResponse(
    val text: String
) : BodyRowResponse {

    override val type: BodyRowType = BodyRowType.MESSAGE

    override fun mapToDomain(): BodyRowModel = MessageModel(
        text = text
    )
}
