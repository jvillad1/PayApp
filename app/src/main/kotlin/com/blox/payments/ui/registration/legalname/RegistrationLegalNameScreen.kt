package com.blox.payments.ui.registration.legalname

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blox.payments.R
import com.blox.uicomponents.commons.BodyLargeText
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthTextField
import com.blox.uicomponents.commons.ScreenTitle
import kotlinx.coroutines.launch

@Composable
fun RegistrationLegalNameScreen(
    onSuccessfulLegalName: () -> Unit
) {
    val viewModel = hiltViewModel<RegistrationLegalNameViewModel>()
    val uiState = viewModel.uiState

    LaunchedEffect(uiState) {
        launch {
            if (uiState.legalNameCompleted) {
                viewModel.legalNameCompletedHandled()
                onSuccessfulLegalName()
            }
        }
    }

    Column {
        ScreenTitle(
            title = stringResource(id = R.string.registration_legal_name_title),
            paddingTop = 40.dp
        )
        BodyLargeText(text = stringResource(id = R.string.registration_legal_name_description))
        FullWidthTextField(
            value = viewModel.firstName,
            hint = stringResource(id = R.string.registration_legal_name_first_name_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        ) {
            viewModel.updateFirstName(it)
        }
        FullWidthTextField(
            value = viewModel.lastName,
            hint = stringResource(id = R.string.registration_legal_name_last_name_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        ) {
            viewModel.updateLastName(it)
        }
        FullWidthButton(text = stringResource(id = R.string.continue_cta)) {
            viewModel.validateLegalName()
        }
    }
}
