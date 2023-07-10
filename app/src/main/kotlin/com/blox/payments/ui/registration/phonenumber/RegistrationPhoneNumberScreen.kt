package com.blox.payments.ui.registration.phonenumber

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.blox.payments.R
import com.blox.payments.R.string
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthClickableTextField
import com.blox.uicomponents.commons.ScreenTitle
import timber.log.Timber

@Composable
fun RegistrationPhoneNumberScreen(
    onSuccessfulPhoneNumber: () -> Unit
) {
    val viewModel = hiltViewModel<RegistrationPhoneNumberViewModel>()
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(title = stringResource(id = R.string.registration_phone_number_title))
        FullWidthClickableTextField(
            value = viewModel.country,
            hint = stringResource(id = string.registration_country_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Go
            ),
            onClick = {
//                showCountryDialog = true
            },
            onValueChange = { Timber.d("no-op") }
        )
        FullWidthButton(text = stringResource(id = string.continue_cta)) {
            viewModel.validatePhoneNumber()
        }
    }
}
