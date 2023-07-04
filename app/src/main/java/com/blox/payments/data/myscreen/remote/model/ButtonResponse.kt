package com.blox.payments.data.myscreen.remote.model

import com.blox.payments.domain.myscreen.model.ButtonModel

data class ButtonResponse(
    val label: String? = null
)

fun ButtonResponse.mapToDomain(): ButtonModel = ButtonModel(
    label = label ?: error("label cannot be null")
)
