package com.blox.payments.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blox.payments.ui.landing.LandingScreen
import com.blox.payments.ui.login.LoginScreen
import com.blox.payments.ui.registration.RegistrationScreen
import com.blox.payments.ui.theme.PayApplicationTheme

@Composable
fun PayAppCompose() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.LANDING
    ) {
        composable(
            route = NavigationRoute.LANDING
        ) {
            LandingScreen()
        }
        composable(
            route = NavigationRoute.LOGIN
        ) {
            LoginScreen()
        }
        composable(
            route = NavigationRoute.REGISTRATION
        ) {
            RegistrationScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PayAppComposePreview() {
    PayApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PayAppCompose()
        }
    }
}