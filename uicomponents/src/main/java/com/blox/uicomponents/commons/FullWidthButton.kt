package com.blox.uicomponents.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FullWidthButton(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = Modifier.fillMaxWidth(.8f)
        ) {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FullWidthButtonPreview() {
    FullWidthButton(
        text = "Continue"
    ) {
    }
}
