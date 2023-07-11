package com.blox.payments.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blox.payments.R
import com.blox.payments.R.drawable
import com.blox.uicomponents.commons.ScreenTitle

@Composable
fun DepositScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = drawable.ic_company),
            contentDescription = stringResource(id = R.string.logo_accessibility),
            modifier = Modifier.padding(top = 40.dp).size(80.dp).align(Alignment.CenterHorizontally)
        )
        ScreenTitle(title = stringResource(id = R.string.deposit_title), paddingTop = 10.dp)
    }
}
