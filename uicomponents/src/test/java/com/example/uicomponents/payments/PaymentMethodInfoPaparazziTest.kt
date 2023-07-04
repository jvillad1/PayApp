package com.example.uicomponents.payments

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test

class PaymentMethodInfoPaparazziTest {

    @get:Rule
    val paparazziRule: Paparazzi = Paparazzi(
        theme = "android:Theme.MaterialComponents.Light.NoActionBar",
        deviceConfig = DeviceConfig.NEXUS_5.copy(softButtons = false, screenHeight = 1),
        renderingMode = SessionParams.RenderingMode.V_SCROLL
    )

    @Test
    fun snapPaymentMethodInfo() {
        paparazziRule.snapshot {
            PaymentMethodInfo(
                imageUrl = "",
                amountPaid = 100.0,
                discount = "50% OFF",
                rawAmount = 100.0,
                methodType = "Dinheiro em conta Mercado Pago"
            )
        }
    }
}
