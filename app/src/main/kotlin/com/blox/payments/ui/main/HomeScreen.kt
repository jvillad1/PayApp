package com.blox.payments.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blox.payments.R
import com.blox.uicomponents.commons.BodyLargeText
import com.blox.uicomponents.commons.ScreenTitle

@Suppress("UnusedPrivateMember")
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BodyLargeText(text = stringResource(id = R.string.home_balance_label), paddingTop = 40.dp)
        ScreenTitle(title = stringResource(id = R.string.home_balance_value, 142260), paddingTop = 10.dp)
    }
}
