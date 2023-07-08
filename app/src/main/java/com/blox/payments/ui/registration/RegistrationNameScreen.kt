package com.blox.payments.ui.registration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.blox.payments.R
import com.blox.uicomponents.common.ScreenTitle

@Composable
fun RegistrationNameScreen(
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit
) {
    ScreenTitle(title = stringResource(id = R.string.registration_legal_name_title))
}
