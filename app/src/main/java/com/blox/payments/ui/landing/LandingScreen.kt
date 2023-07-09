package com.blox.payments.ui.landing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LandingScreen() {
    val viewModel = hiltViewModel<LandingViewModel>()
    val uiState = viewModel.uiState

    LaunchedEffect(uiState) {
//        launch {
//            when {
//                uiState.firstNameError -> onClick(uiState.myScreenIdentifier)
//            }
//
//            fetchViewModel.navigationHandled()
//        }
    }

    when {
        !uiState.legalNameCompleted -> WelcomeScreen(viewModel)
        uiState.legalNameCompleted -> SplashScreen(viewModel)
    }
}

@Composable
fun SplashScreen(viewModel: LandingViewModel) {

}

@Composable
fun WelcomeScreen(viewModel: LandingViewModel) {

}
