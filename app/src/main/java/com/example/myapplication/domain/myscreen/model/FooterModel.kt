package com.example.myapplication.domain.myscreen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FooterModel(
    val buttonModelList: List<ButtonModel>
) : Parcelable
