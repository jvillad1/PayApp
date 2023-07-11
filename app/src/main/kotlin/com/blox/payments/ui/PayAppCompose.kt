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
import com.blox.payments.ui.landing.WelcomeScreen
import com.blox.payments.ui.login.ForgotPasswordScreen
import com.blox.payments.ui.login.SignInScreen
import com.blox.payments.ui.main.HomeScreen
import com.blox.payments.ui.navigation.LoginNavigationRoute
import com.blox.payments.ui.navigation.MainNavigationRoute
import com.blox.payments.ui.navigation.NavigationGraph
import com.blox.payments.ui.navigation.RegistrationNavigationRoute
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
        startDestination = NavigationGraph.Welcome.route
    ) {
        composable(
            route = NavigationGraph.Welcome.route
        ) {
            WelcomeScreen { destination ->
                navController.navigate(destination)
            }
        }
        configureLoginGraph(navController)
        configureRegistrationGraph(navController)
        configureMainGraph(navController)
    }
}

private fun NavGraphBuilder.configureLoginGraph(navController: NavHostController) {
    navigation(
        startDestination = LoginNavigationRoute.SignIn.route,
        route = NavigationGraph.Login.route
    ) {
        composable(
            route = LoginNavigationRoute.SignIn.route
        ) {
            SignInScreen(
                viewModel = it.sharedViewModel(navController = navController),
                onSuccessfulLogin = {
                    navController.navigate(NavigationGraph.Main.route) {
                        popUpTo(NavigationGraph.Login.route) {
                            inclusive = true
                        }
                    }
                },
                onForgotPassword = {
                    navController.navigate(LoginNavigationRoute.ForgotPassword.route)
                }
            )
        }
        composable(
            route = LoginNavigationRoute.ForgotPassword.route
        ) {
            ForgotPasswordScreen()
        }
    }
}

private fun NavGraphBuilder.configureRegistrationGraph(navController: NavHostController) {
    navigation(
        startDestination = RegistrationNavigationRoute.RefCode.route,
        route = NavigationGraph.Registration.route
    ) {
        composable(
            route = RegistrationNavigationRoute.RefCode.route
        ) {
            RegistrationRefCodeScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(RegistrationNavigationRoute.LegalName.route)
            }
        }
        composable(
            route = RegistrationNavigationRoute.LegalName.route
        ) {
            RegistrationLegalNameScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(RegistrationNavigationRoute.BirthDate.route)
            }
        }
        composable(
            route = RegistrationNavigationRoute.BirthDate.route
        ) {
            RegistrationBirthDateScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(RegistrationNavigationRoute.Country.route)
            }
        }
        composable(
            route = RegistrationNavigationRoute.Country.route
        ) {
            RegistrationCountryScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(
                    RegistrationNavigationRoute.PhoneNumber.route
                )
            }
        }
        composable(
            route = RegistrationNavigationRoute.PhoneNumber.route
        ) {
            RegistrationPhoneNumberScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(RegistrationNavigationRoute.Email.route)
            }
        }
        composable(
            route = RegistrationNavigationRoute.Email.route
        ) {
            RegistrationEmailScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(RegistrationNavigationRoute.Password.route)
            }
        }
        composable(
            route = RegistrationNavigationRoute.Password.route
        ) {
            RegistrationPasswordScreen(
                viewModel = it.sharedViewModel(navController = navController)
            ) {
                navController.navigate(NavigationGraph.Main.route) {
                    popUpTo(NavigationGraph.Registration.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.configureMainGraph(navController: NavHostController) {
    navigation(
        startDestination = MainNavigationRoute.Home.route,
        route = NavigationGraph.Main.route
    ) {
        composable(
            route = MainNavigationRoute.Home.route
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
