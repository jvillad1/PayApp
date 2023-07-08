package com.blox.payments.ui.registration

data class RegistrationUiState(
    val firstName: String? = null,
    val lastName: String? = null,
    val legalNameCompleted: Boolean = false,
    val firstNameError: Boolean = false,
    val lastNameError: Boolean = false
)
