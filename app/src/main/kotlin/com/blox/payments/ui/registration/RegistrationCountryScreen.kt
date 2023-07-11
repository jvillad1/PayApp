package com.blox.payments.ui.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.blox.payments.R
import com.blox.payments.domain.registration.model.Countries
import com.blox.uicomponents.commons.CountryCodePickerDialog
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthClickableTextField
import com.blox.uicomponents.commons.ScreenTitle
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun RegistrationCountryScreen(
    viewModel: RegistrationViewModel,
    onSuccessfulCountry: (String) -> Unit
) {
    val uiState = viewModel.uiState
    var showCountryDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        launch {
            if (uiState.countryCompleted) {
                viewModel.countryCompletedHandled()
                onSuccessfulCountry(viewModel.country)
            }
        }
    }

    if (showCountryDialog) {
        CountryCodePickerDialog(
            countries = Countries.toCountriesList(),
            onSelection = {
                viewModel.country = it.name
            },
            dismiss = { showCountryDialog = false }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title = stringResource(id = R.string.registration_country_title)
        )
        FullWidthClickableTextField(
            value = viewModel.country,
            hint = stringResource(id = R.string.registration_country_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onClick = {
                showCountryDialog = true
            },
            onValueChange = { Timber.d("no-op") }
        )
        FullWidthButton(text = stringResource(id = R.string.continue_cta)) {
            viewModel.validateCountry()
        }
    }
}
