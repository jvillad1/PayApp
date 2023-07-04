package com.blox.payments.domain.myscreen.model

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NestedBodyModel(
    val sectionTitle: String,
    val bodyRowModel: List<BodyRowModel>
) : BodyRowModel {

    @IgnoredOnParcel
    override val type: BodyRowType = BodyRowType.SECTION
}
