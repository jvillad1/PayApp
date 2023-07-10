package com.blox.payments.ui.login

data class LoginUiState(
    val legalNameCompleted: Boolean = false,
    val firstNameError: Boolean = false,
    val lastNameError: Boolean = false
)
