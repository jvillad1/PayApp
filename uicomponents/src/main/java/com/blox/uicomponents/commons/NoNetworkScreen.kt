package com.blox.uicomponents.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uicomponents.R
import kotlinx.coroutines.launch

@Composable
fun NoNetworkScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp),
                painter = painterResource(R.drawable.ic_offline),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.network_error_message),
                color = MaterialTheme.colorScheme.onBackground
            )
            Button(
                onClick = {
                    onClick.invoke()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.network_error_cta),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoNetworkPreview() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        NoNetworkScreen(Modifier.padding(padding)) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    "Try again clicked"
                )
            }
        }
    }
}
