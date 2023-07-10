package com.blox.payments.ui.registration.phonenumber

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.blox.payments.R
import com.blox.payments.R.string
import com.blox.payments.domain.registration.model.Countries
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthTextField
import com.blox.uicomponents.commons.ScreenTitle
import kotlinx.coroutines.launch

@Composable
fun RegistrationPhoneNumberScreen(
    onSuccessfulPhoneNumber: () -> Unit
) {
    val viewModel = hiltViewModel<RegistrationPhoneNumberViewModel>()
    val uiState = viewModel.uiState

    LaunchedEffect(uiState) {
        launch {
            if (uiState.phoneNumberCompleted) {
                viewModel.phoneNumberCompletedHandled()
                onSuccessfulPhoneNumber()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(title = stringResource(id = R.string.registration_phone_number_title))
        FullWidthTextField(
            value = viewModel.phoneNumber,
            hint = stringResource(id = string.registration_phone_number_label),
            prefix = {
                Text(
                    Countries.getCountryCodeByName(viewModel.selectedCountry.orEmpty())
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            )
        ) {
            viewModel.updatePhoneNumber(it)
        }
        FullWidthButton(text = stringResource(id = string.continue_cta)) {
            viewModel.validatePhoneNumber()
        }
    }
}
