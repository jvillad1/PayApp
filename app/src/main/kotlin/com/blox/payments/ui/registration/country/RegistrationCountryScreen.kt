package com.blox.payments.ui.registration.country

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.blox.payments.R
import com.blox.payments.R.string
import com.blox.uicomponents.commons.CountryCodePickerDialog
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthClickableTextField
import com.blox.uicomponents.commons.ScreenTitle
import com.blox.uicomponents.model.Country
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun RegistrationCountryScreen(
    onSuccessfulCountry: () -> Unit
) {
    val viewModel = hiltViewModel<RegistrationCountryViewModel>()
    val uiState = viewModel.uiState
    var showCountryDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        launch {
            if (uiState.countryCompleted) {
                viewModel.countryCompletedHandled()
                onSuccessfulCountry()
            }
        }
    }

    if (showCountryDialog) {
        CountryCodePickerDialog(
            countries = listOf(
                Country("Brazil"),
                Country("Colombia"),
                Country("Mexico"),
                Country("Peru"),
                Country("United States")
            ),
            onSelection = {
                viewModel.updateCountry(it.name)
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
                imeAction = ImeAction.Go
            ),
            onClick = {
                showCountryDialog = true
            },
            onValueChange = { Timber.d("no-op") }
        )
        FullWidthButton(text = stringResource(id = string.continue_cta)) {
            viewModel.validateCountry()
        }
    }
}
