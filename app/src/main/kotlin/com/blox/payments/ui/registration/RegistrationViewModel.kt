package com.blox.payments.ui.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blox.payments.domain.registration.usecases.ValidateBirthDate
import com.blox.payments.domain.registration.usecases.ValidateCountry
import com.blox.payments.domain.registration.usecases.ValidateEmail
import com.blox.payments.domain.registration.usecases.ValidateLegalName
import com.blox.payments.domain.registration.usecases.ValidatePassword
import com.blox.payments.domain.registration.usecases.ValidatePhoneNumber
import com.blox.payments.domain.registration.usecases.WithRefCode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateBirthDate: ValidateBirthDate,
    private val validateCountry: ValidateCountry,
    private val validateEmail: ValidateEmail,
    private val validateLegalName: ValidateLegalName,
    private val validatePassword: ValidatePassword,
    private val validatePhoneNumber: ValidatePhoneNumber,
    private val withRefCode: WithRefCode
) : ViewModel() {

    private var fetchJob: Job? = null

    var uiState by mutableStateOf(RegistrationUiState())
        private set

    var refCode by mutableStateOf("")
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var birthDate by mutableStateOf("")
    var country by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun withRefCode() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("Ref code: $refCode")
            withRefCode.invoke(refCode)
        }
    }

    fun validateLegalName() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("First name: $firstName and Last name: $lastName")
            validateLegalName.invoke(firstName, lastName)
                .onSuccess { isValid ->
                    Timber.d("This is a success")
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(legalNameCompleted = isValid)
                    }
                }
                .onFailure { throwable ->
                    Timber.wtf(throwable, "This is a failure")
                }
        }
    }

    fun legalNameCompletedHandled() {
        uiState = uiState.copy(legalNameCompleted = false)
    }

    fun validateBirthDate() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("Birth Date: $birthDate")
            validateBirthDate.invoke(birthDate)
                .onSuccess { isValid ->
                    Timber.d("This is a success")
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(birthDateCompleted = isValid)
                    }
                }
                .onFailure { throwable ->
                    Timber.wtf(throwable, "This is a failure")
                }
        }
    }

    fun birthDateCompletedHandled() {
        uiState = uiState.copy(birthDateCompleted = false)
    }

    fun validateCountry() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("Country: $country")
            validateCountry.invoke(country)
                .onSuccess { isValid ->
                    Timber.d("This is a success")
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(countryCompleted = isValid)
                    }
                }
                .onFailure { throwable ->
                    Timber.wtf(throwable, "This is a failure")
                }
        }
    }

    fun countryCompletedHandled() {
        uiState = uiState.copy(countryCompleted = false)
    }

    fun validatePhoneNumber() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("Phone Number: $phoneNumber")
            validatePhoneNumber.invoke(phoneNumber)
                .onSuccess { isValid ->
                    Timber.d("This is a success")
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(phoneNumberCompleted = isValid)
                    }
                }
                .onFailure { throwable ->
                    Timber.wtf(throwable, "This is a failure")
                }
        }
    }

    fun phoneNumberCompletedHandled() {
        uiState = uiState.copy(phoneNumberCompleted = false)
    }

    fun validateEmail() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("Email: $email")
            validateEmail.invoke(email)
                .onSuccess { isValid ->
                    Timber.d("This is a success")
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(emailCompleted = isValid)
                    }
                }
                .onFailure { throwable ->
                    Timber.wtf(throwable, "This is a failure")
                }
        }
    }

    fun emailCompletedHandled() {
        uiState = uiState.copy(emailCompleted = false)
    }

    fun validatePassword() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("Password: $password")
            validatePassword.invoke(password)
                .onSuccess { isValid ->
                    Timber.d("This is a success")
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(passwordCompleted = isValid)
                    }
                }
                .onFailure { throwable ->
                    Timber.wtf(throwable, "This is a failure")
                }
        }
    }

    fun passwordCompletedHandled() {
        uiState = uiState.copy(passwordCompleted = false)
    }
}
