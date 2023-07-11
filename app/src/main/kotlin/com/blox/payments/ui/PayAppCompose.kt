package com.blox.payments.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.blox.payments.ui.home.HomeScreen
import com.blox.payments.ui.landing.WelcomeScreen
import com.blox.payments.ui.login.ForgotPasswordScreen
import com.blox.payments.ui.login.LoginScreen
import com.blox.payments.ui.registration.RegistrationBirthDateScreen
import com.blox.payments.ui.registration.RegistrationCountryScreen
import com.blox.payments.ui.registration.RegistrationEmailScreen
import com.blox.payments.ui.registration.RegistrationLegalNameScreen
import com.blox.payments.ui.registration.RegistrationPasswordScreen
import com.blox.payments.ui.registration.RegistrationPhoneNumberScreen
import com.blox.payments.ui.registration.RegistrationRefCodeScreen
import com.blox.payments.ui.theme.PayApplicationTheme

@Suppress("LongMethod")
@Composable
fun PayAppCompose() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.AUTH
    ) {
        configureAuthGraph(navController)

        configureMainGraph(navController)
    }
}

private fun NavGraphBuilder.configureAuthGraph(navController: NavHostController) {
    navigation(
        startDestination = AuthNavigationRoute.WELCOME,
        route = NavigationGraph.AUTH
    ) {
        composable(
            route = AuthNavigationRoute.WELCOME
        ) {
            WelcomeScreen { destination ->
                navController.navigate(destination)
            }
        }
        composable(
            route = AuthNavigationRoute.LOGIN
        ) {
            LoginScreen(
                viewModel = it.sharedViewModel(navController = navController),
                onSuccessfulLogin = {
                    navController.navigate(NavigationGraph.MAIN) {
                        popUpTo(NavigationGraph.AUTH) {
                            inclusive = true
                        }
                    }
                },
                onForgotPassword = {
                    navController.navigate(AuthNavigationRoute.FORGOT_PASSWORD)
                }
            )
        }
        composable(
            route = AuthNavigationRoute.FORGOT_PASSWORD
        ) {
            ForgotPasswordScreen()
        }
        composable(
            route = AuthNavigationRoute.REGISTRATION_REF_CODE
        ) {
            RegistrationRefCodeScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(AuthNavigationRoute.REGISTRATION_LEGAL_NAME)
            }
        }
        composable(
            route = AuthNavigationRoute.REGISTRATION_LEGAL_NAME
        ) {
            RegistrationLegalNameScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(AuthNavigationRoute.REGISTRATION_BIRTH_DATE)
            }
        }
        composable(
            route = AuthNavigationRoute.REGISTRATION_BIRTH_DATE
        ) {
            RegistrationBirthDateScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(AuthNavigationRoute.REGISTRATION_COUNTRY)
            }
        }
        composable(
            route = AuthNavigationRoute.REGISTRATION_COUNTRY
        ) {
            RegistrationCountryScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(
                    AuthNavigationRoute.REGISTRATION_PHONE_NUMBER
                )
            }
        }
        composable(
            route = AuthNavigationRoute.REGISTRATION_PHONE_NUMBER
        ) {
            RegistrationPhoneNumberScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(AuthNavigationRoute.REGISTRATION_EMAIL)
            }
        }
        composable(
            route = AuthNavigationRoute.REGISTRATION_EMAIL
        ) {
            RegistrationEmailScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(AuthNavigationRoute.REGISTRATION_PASSWORD)
            }
        }
        composable(
            route = AuthNavigationRoute.REGISTRATION_PASSWORD
        ) {
            RegistrationPasswordScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(NavigationGraph.MAIN) {
                    popUpTo(NavigationGraph.AUTH) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.configureMainGraph(navController: NavHostController) {
    navigation(
        startDestination = MainNavigationRoute.HOME,
        route = NavigationGraph.MAIN
    ) {
        composable(
            route = MainNavigationRoute.HOME
        ) {
            HomeScreen()
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
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
