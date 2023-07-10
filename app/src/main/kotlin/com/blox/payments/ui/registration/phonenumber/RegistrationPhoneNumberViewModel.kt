package com.blox.payments.ui.registration.phonenumber

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blox.payments.domain.registration.usecases.ValidatePhoneNumber
import com.blox.payments.ui.NavigationArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltViewModel
class RegistrationPhoneNumberViewModel @Inject constructor(
    private val validatePhoneNumber: ValidatePhoneNumber,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var fetchJob: Job? = null

    val selectedCountry: String? = savedStateHandle[NavigationArgument.COUNTRY]
    var uiState by mutableStateOf(RegistrationPhoneNumberState())
        private set

    var phoneNumber by mutableStateOf("")
        private set

    fun updatePhoneNumber(input: String) {
        phoneNumber = input
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
}
