package com.blox.payments.ui.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blox.payments.domain.registration.usecases.ValidateLegalName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateLegalName: ValidateLegalName
) : ViewModel() {

    private var fetchJob: Job? = null

    var uiState by mutableStateOf(RegistrationUiState())
        private set

    fun updateFirstName(firstNameInput: String) {
        uiState = uiState.copy(firstName = firstNameInput)
    }

    fun updateLastName(lastNameInput: String) {
        uiState = uiState.copy(lastName = lastNameInput)
    }

    fun validateLegalName() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("First name: ${uiState.firstName} and Last name: ${uiState.lastName}")
            validateLegalName.invoke(uiState.firstName, uiState.lastName)
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
}
