package com.blox.payments.ui.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.blox.payments.R
import com.blox.uicomponents.commons.BodyLargeText
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthTextField
import com.blox.uicomponents.commons.ScreenTitle
import timber.log.Timber

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit
) {
    Column {
        ScreenTitle(title = stringResource(id = R.string.registration_legal_name_title))
        BodyLargeText(text = stringResource(id = R.string.registration_legal_name_description))
        FullWidthTextField(
            hint = stringResource(id = R.string.registration_first_name_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text
            )
        )
        FullWidthTextField(
            hint = stringResource(id = R.string.registration_last_name_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text
            )
        )
        FullWidthButton(text = stringResource(id = R.string.registration_continue_cta)) {
            Timber.d("Call VM and check if it can continue")
        }
    }
}
