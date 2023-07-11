package com.blox.payments.ui.registration

data class RegistrationUiState(
    val legalNameCompleted: Boolean = false,
    val firstNameError: Boolean = false,
    val lastNameError: Boolean = false,
    val birthDateCompleted: Boolean = false,
    val birthDateError: Boolean = false,
    val countryCompleted: Boolean = false,
    val countryError: Boolean = false,
    val phoneNumberCompleted: Boolean = false,
    val phoneNumberError: Boolean = false,
    val emailCompleted: Boolean = false,
    val emailError: Boolean = false,
    val passwordCompleted: Boolean = false,
    val passwordError: Boolean = false
)
