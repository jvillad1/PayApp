package com.blox.uicomponents.commons

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test

class AutoSizedCircularProgressIndicatorPaparazziTest {

    @get:Rule
    val paparazziRule: Paparazzi = Paparazzi(
        theme = "android:Theme.MaterialComponents.Light.NoActionBar",
        deviceConfig = DeviceConfig.NEXUS_5.copy(softButtons = false, screenHeight = 1),
        renderingMode = SessionParams.RenderingMode.V_SCROLL
    )

    @Test
    fun snapAutoSizedCircularProgressIndicator() {
        paparazziRule.snapshot {
            AutoSizedCircularProgressIndicator()
        }
    }
}
