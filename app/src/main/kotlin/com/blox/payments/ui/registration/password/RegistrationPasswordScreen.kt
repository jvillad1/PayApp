package com.blox.payments.ui.registration.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.blox.payments.R
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthPasswordTextField
import com.blox.uicomponents.commons.ScreenTitle
import kotlinx.coroutines.launch

@Composable
fun RegistrationPasswordScreen(
    onSuccessfulEmail: () -> Unit
) {
    val viewModel = hiltViewModel<RegistrationPasswordViewModel>()
    val uiState = viewModel.uiState

    LaunchedEffect(uiState) {
        launch {
            if (uiState.passwordCompleted) {
                viewModel.passwordCompletedHandled()
                onSuccessfulEmail()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(title = stringResource(id = R.string.registration_password_title))
        FullWidthPasswordTextField(
            value = viewModel.password,
            hint = stringResource(id = R.string.registration_password_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        ) {
            viewModel.updatePassword(it)
        }
        FullWidthButton(text = stringResource(id = R.string.continue_cta)) {
            viewModel.validatePassword()
        }
    }
}
