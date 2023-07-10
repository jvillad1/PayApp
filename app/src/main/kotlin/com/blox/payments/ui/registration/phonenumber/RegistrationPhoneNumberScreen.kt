package com.blox.payments.ui.registration.phonenumber

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.blox.payments.R
import com.blox.uicomponents.commons.ScreenTitle

@Composable
fun RegistrationPhoneNumberScreen(
    onSuccessfulPhoneNumber: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(title = stringResource(id = R.string.registration_phone_number_title))
    }
}
