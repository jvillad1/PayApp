package com.blox.payments.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blox.payments.ui.registration.RegistrationNameScreen
import com.blox.payments.ui.theme.PayApplicationTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun PayAppCompose(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.REGISTRATION_NAME
    ) {
        composable(
            route = NavigationRoute.REGISTRATION_NAME
        ) {
            RegistrationNameScreen(onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PayAppComposePreview(
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    PayApplicationTheme {
        PayAppCompose()
    }
}
