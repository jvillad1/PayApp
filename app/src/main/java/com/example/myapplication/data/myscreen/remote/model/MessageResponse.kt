package com.example.myapplication.data.myscreen.remote.model

import com.example.myapplication.domain.myscreen.model.BodyRowModel
import com.example.myapplication.domain.myscreen.model.BodyRowType
import com.example.myapplication.domain.myscreen.model.MessageModel

data class MessageResponse(
    val text: String
) : BodyRowResponse {

    override val type: BodyRowType = BodyRowType.MESSAGE

    override fun mapToDomain(): BodyRowModel = MessageModel(
        text = text
    )
}
