package com.blox.payments.ui.registration.legalname

data class RegistrationLegalNameUiState(
    val legalNameCompleted: Boolean = false,
    val firstNameError: Boolean = false,
    val lastNameError: Boolean = false
)
