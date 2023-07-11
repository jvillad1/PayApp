package com.blox.payments.domain.home.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import java.math.BigInteger

@Keep
@Parcelize
data class Transaction(
    val id: Int,
    val transactionType: String,
    val amount: String,
    val creationTime: String,
    val balance: String
) : Parcelable
