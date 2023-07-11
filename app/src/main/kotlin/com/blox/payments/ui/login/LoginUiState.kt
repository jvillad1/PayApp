package com.blox.payments.ui.login

data class LoginUiState(
    val loginCompleted: Boolean = false,
    val emailError: Boolean = false,
    val passwordError: Boolean = false
)
