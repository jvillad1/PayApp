package com.blox.payments.ui.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.blox.payments.R
import com.blox.payments.ui.utlis.showDatePickerDialog
import com.blox.uicomponents.commons.BodyLargeText
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthClickableTextField
import com.blox.uicomponents.commons.ScreenTitle
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun RegistrationBirthDateScreen(
    viewModel: RegistrationViewModel,
    onSuccessfulBirthDate: () -> Unit
) {
    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        launch {
            if (uiState.birthDateCompleted) {
                viewModel.birthDateCompletedHandled()
                onSuccessfulBirthDate()
            }
        }
    }

    Column {
        ScreenTitle(
            title = stringResource(id = R.string.registration_birth_date_title),
            paddingTop = 40.dp
        )
        BodyLargeText(text = stringResource(id = R.string.registration_birth_date_description))
        FullWidthClickableTextField(
            value = viewModel.birthDate,
            hint = stringResource(id = R.string.registration_birth_date_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            onClick = {
                showDatePickerDialog(context, viewModel.birthDate) {
                    viewModel.birthDate = it
                }
            },
            onValueChange = { Timber.d("no-op") }
        )
        FullWidthButton(text = stringResource(id = R.string.continue_cta)) {
            viewModel.validateBirthDate()
        }
    }
}
