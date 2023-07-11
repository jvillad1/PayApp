package com.blox.uicomponents.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BodyLargeText(
    text: String,
    paddingTop: Dp = 0.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                top = paddingTop,
                end = 20.dp
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BodyLargeTextPreview() {
    BodyLargeText("Please enter your legal name, which must match your government ID.")
}
