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
import com.blox.payments.ui.home.HomeScreen
import com.blox.payments.ui.landing.WelcomeScreen
import com.blox.payments.ui.login.LoginScreen
import com.blox.payments.ui.registration.RegistrationScreen
import com.blox.payments.ui.theme.PayApplicationTheme

@Composable
fun PayAppCompose() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.WELCOME
    ) {
        composable(
            route = NavigationRoute.WELCOME
        ) {
            WelcomeScreen { destination ->
                navController.navigate(destination)
            }
        }
        composable(
            route = NavigationRoute.LOGIN
        ) {
            LoginScreen(
                onSuccessfulLogin = { navController.navigate(NavigationRoute.HOME) },
                onForgotPassword = { navController.navigate(NavigationRoute.FORGOT_PASSWORD) }
            )
        }
        composable(
            route = NavigationRoute.FORGOT_PASSWORD
        ) {
            RegistrationScreen()
        }
        composable(
            route = NavigationRoute.REGISTRATION
        ) {
            RegistrationScreen()
        }
        composable(
            route = NavigationRoute.HOME
        ) {
            HomeScreen()
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
