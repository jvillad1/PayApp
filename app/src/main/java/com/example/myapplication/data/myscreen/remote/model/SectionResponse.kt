package com.example.myapplication.data.myscreen.remote.model

import com.example.myapplication.domain.myscreen.model.BodyRowModel
import com.example.myapplication.domain.myscreen.model.BodyRowType
import com.example.myapplication.domain.myscreen.model.NestedBodyModel

data class SectionResponse(
    val sectionTitle: String,
    val bodyRow: List<BodyRowResponse>
) : BodyRowResponse {

    override val type: BodyRowType = BodyRowType.SECTION

    override fun mapToDomain(): BodyRowModel = NestedBodyModel(
        sectionTitle = sectionTitle,
        bodyRowModel = bodyRow.map { it.mapToDomain() }
    )
}
