package com.blox.payments.data.myscreen.remote.model

import com.blox.payments.domain.myscreen.model.BodyRowModel
import com.blox.payments.domain.myscreen.model.BodyRowType

sealed interface BodyRowResponse {
    val type: BodyRowType

    fun mapToDomain(): BodyRowModel
}
