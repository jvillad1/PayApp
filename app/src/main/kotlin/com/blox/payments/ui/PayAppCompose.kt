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
import com.blox.payments.ui.login.ForgotPasswordScreen
import com.blox.payments.ui.login.LoginScreen
import com.blox.payments.ui.registration.birthdate.RegistrationBirthDateScreen
import com.blox.payments.ui.registration.country.RegistrationCountryScreen
import com.blox.payments.ui.registration.legalname.RegistrationLegalNameScreen
import com.blox.payments.ui.registration.phonenumber.RegistrationPhoneNumberScreen
import com.blox.payments.ui.registration.refcode.RegistrationRefCodeScreen
import com.blox.payments.ui.theme.PayApplicationTheme

@Suppress("LongMethod")
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
            ForgotPasswordScreen()
        }
        composable(
            route = NavigationRoute.REGISTRATION_REF_CODE
        ) {
            RegistrationRefCodeScreen {
                navController.navigate(NavigationRoute.REGISTRATION_LEGAL_NAME)
            }
        }
        composable(
            route = NavigationRoute.REGISTRATION_LEGAL_NAME
        ) {
            RegistrationLegalNameScreen {
                navController.navigate(NavigationRoute.REGISTRATION_BIRTH_DATE)
            }
        }
        composable(
            route = NavigationRoute.REGISTRATION_BIRTH_DATE
        ) {
            RegistrationBirthDateScreen {
                navController.navigate(NavigationRoute.REGISTRATION_COUNTRY)
            }
        }
        composable(
            route = NavigationRoute.REGISTRATION_COUNTRY
        ) {
            RegistrationCountryScreen { selectedCountry ->
                navController.navigate(
                    "${NavigationRoute.REGISTRATION_PHONE_NUMBER}/$selectedCountry"
                )
            }
        }
        composable(
            route = "${NavigationRoute.REGISTRATION_PHONE_NUMBER}/{${NavigationArgument.COUNTRY}}"
        ) {
            RegistrationPhoneNumberScreen {
                navController.navigate(NavigationRoute.HOME)
            }
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
