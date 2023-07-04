package com.example.myapplication.domain.myscreen.model

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CrossSellingModel(
    val text: String
) : BodyRowModel {

    @IgnoredOnParcel
    override val type: BodyRowType = BodyRowType.CROSS_SELLING
}
