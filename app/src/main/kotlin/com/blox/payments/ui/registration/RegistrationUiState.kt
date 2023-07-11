package com.blox.payments.ui.registration

data class RegistrationUiState(
    val legalNameCompleted: Boolean = false,
    val firstNameError: Boolean = false,
    val lastNameError: Boolean = false,
    val birthDateCompleted: Boolean = false,
    val birthDateError: Boolean = false
)
