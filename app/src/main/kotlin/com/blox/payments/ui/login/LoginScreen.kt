package com.blox.payments.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blox.payments.BuildConfig
import com.blox.payments.R
import com.blox.payments.R.drawable
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthPasswordTextField
import com.blox.uicomponents.commons.FullWidthTextField
import com.blox.uicomponents.commons.ScreenTitle

@Composable
fun LoginScreen(
    onSuccessfulLogin: () -> Unit,
    onForgotPassword: () -> Unit
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = drawable.ic_direct_pay),
            contentDescription = stringResource(id = R.string.logo_accessibility),
            modifier = Modifier.padding(top = 40.dp)
                .size(80.dp)
                .align(Alignment.CenterHorizontally)
        )
        ScreenTitle(title = stringResource(id = R.string.login_title), paddingTop = 10.dp)
        FullWidthTextField(
            hint = stringResource(id = R.string.login_email_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        ) {
            viewModel.updateFirstName(it.text)
        }
        FullWidthPasswordTextField(
            hint = stringResource(id = R.string.login_password_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        ) {
            viewModel.updateFirstName(it.text)
        }
        FullWidthButton(
            text = stringResource(id = R.string.login_continue_cta),
            onClick = {
                // TODO: Call VM -> Backend Before navigation
                onSuccessfulLogin()
            },
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        TextButton(
            onClick = { onForgotPassword() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.login_forgot_password_label))
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(
                id = R.string.login_version_name,
                BuildConfig.VERSION_NAME.removeSuffix("-${BuildConfig.FLAVOR}")
            ),
            modifier = Modifier.padding(bottom = 20.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
