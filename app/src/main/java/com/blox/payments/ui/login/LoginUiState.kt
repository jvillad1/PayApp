package com.blox.payments.ui.login

data class LoginUiState(
    val firstName: String? = null,
    val lastName: String? = null,
    val legalNameCompleted: Boolean = false,
    val firstNameError: Boolean = false,
    val lastNameError: Boolean = false
)
