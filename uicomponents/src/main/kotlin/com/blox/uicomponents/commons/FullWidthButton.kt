package com.blox.uicomponents.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun FullWidthButton(
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
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
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Timber.d("$padding")
        FullWidthButton(
            text = "Continue"
        ) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    "Continue Button clicked"
                )
            }
        }
    }
}
