package com.blox.payments.ui.registration.refcode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Composable
fun RegistrationRefCodeScreen(
    onContinue: () -> Unit
) {
    val viewModel = hiltViewModel<RegistrationRefCodeViewModel>()

    Column {
        Spacer(modifier = Modifier.padding(top = 40.dp))
        BodyLargeText(text = stringResource(id = R.string.registration_ref_code_description))
        FullWidthTextField(
            value = viewModel.refCode,
            hint = stringResource(id = R.string.registration_ref_code_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        ) {
            viewModel.updateRefCode(it)
        }
        FullWidthButton(text = stringResource(id = R.string.continue_cta)) {
            viewModel.withRefCode()
            onContinue()
        }
    }
}
