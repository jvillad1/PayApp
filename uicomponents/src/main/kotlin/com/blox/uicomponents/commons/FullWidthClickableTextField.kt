package com.blox.uicomponents.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Suppress("LongParameterList", "MagicNumber")
@Composable
fun FullWidthClickableTextField(
    value: String,
    hint: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { fieldValue ->
                onValueChange(fieldValue)
            },
            modifier = Modifier.fillMaxWidth(.8f).clickable {
                onClick()
            },
            enabled = false,
            textStyle = TextStyle.Default,
            label = { Text(text = hint) },
            keyboardOptions = keyboardOptions,
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledTextColor = MaterialTheme.colorScheme.outline,
                disabledLabelColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FullWidthClickableTextFieldPreview() {
    FullWidthClickableTextField(
        value = "",
        hint = "First Name",
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text
        ),
        onClick = {
            Timber.d("TextField clicked")
        },
        onValueChange = {
            Timber.d("TextField state updated with: $it")
        }
    )
}
