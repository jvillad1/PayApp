package com.example.myapplication.ui.sections.body

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.myscreen.model.BodyRowModel
import com.example.myapplication.domain.myscreen.model.CrossSellingModel
import com.example.myapplication.domain.myscreen.model.MessageModel
import com.example.myapplication.domain.myscreen.model.NestedBodyModel
import com.example.myapplication.domain.myscreen.model.PaymentMethodInfoModel
import com.example.uicomponents.payments.PaymentMethodInfo
import timber.log.Timber

@Composable
fun BodyModelMapper(
    bodyRowModel: List<BodyRowModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        bodyRowModel.map { model ->
            when (model) {
                is CrossSellingModel -> item {
                    // CrossSelling(...)
                    Text(
                        text = model.text,
                        color = Color.Black.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center
                    )
                }

                is MessageModel -> item {
                    // Message(...)
                    Text(
                        text = model.text,
                        color = Color.Black.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center
                    )
                }

                is NestedBodyModel -> item {
                    NestedBodySection(
                        section = model,
                        modifier = modifier
                    )
                }

                is PaymentMethodInfoModel -> item {
                    PaymentMethodInfo(
                        imageUrl = "",
                        amountPaid = 100.0,
                        discount = "50% OFF",
                        rawAmount = 100.0,
                        methodType = model.methodType
                    )
                }
            }
        }
    }
}

@Composable
fun NestedBodyModelMapper(
    bodyRowModel: List<BodyRowModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        bodyRowModel.map { model ->
            when (model) {
                is CrossSellingModel ->
                    // CrossSelling(...)
                    Text(
                        text = model.text,
                        color = Color.Black.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center
                    )

                is MessageModel ->
                    // Message(...)
                    Text(
                        text = model.text,
                        color = Color.Black.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center
                    )

                is PaymentMethodInfoModel -> PaymentMethodInfo(
                    imageUrl = "",
                    amountPaid = 100.0,
                    discount = "50% OFF",
                    rawAmount = 100.0,
                    methodType = model.methodType
                )

                is NestedBodyModel -> Timber.d("no-op")
            }
        }
    }
}
