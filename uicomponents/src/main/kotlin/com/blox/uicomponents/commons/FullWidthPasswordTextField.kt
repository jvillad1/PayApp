package com.blox.uicomponents.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uicomponents.R
import timber.log.Timber

@Composable
fun FullWidthPasswordTextField(
    hint: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (TextFieldValue) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var showPassword by remember { mutableStateOf(value = false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { fieldValue ->
                text = fieldValue
                onValueChange(fieldValue)
            },
            modifier = Modifier.fillMaxWidth(.8f),
            textStyle = TextStyle.Default,
            label = { Text(text = hint) },
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.ic_visibility_filled
                            ),
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.ic_visibility_off_filled
                            ),
                            contentDescription = "hide_password"
                        )
                    }
                }
            },
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = keyboardOptions
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FullWidthPasswordTextFieldPreview() {
    FullWidthPasswordTextField(
        hint = "First Name",
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text
        )
    ) {
        Timber.d("TextField state updated with: $it")
    }
}
