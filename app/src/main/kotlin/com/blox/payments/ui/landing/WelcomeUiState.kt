package com.blox.payments.ui.landing

data class WelcomeUiState(
    val firstName: String? = null,
    val lastName: String? = null,
    val legalNameCompleted: Boolean = false,
    val firstNameError: Boolean = false,
    val lastNameError: Boolean = false
)
