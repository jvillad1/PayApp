package com.blox.payments.ui.registration.legalname

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
class RegistrationLegalNameViewModel @Inject constructor(
    private val validateLegalName: ValidateLegalName
) : ViewModel() {

    private var fetchJob: Job? = null

    var uiState by mutableStateOf(RegistrationLegalNameUiState())
        private set

    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")
        private set

    fun updateFirstName(input: String) {
        firstName = input
    }

    fun updateLastName(input: String) {
        lastName = input
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
}
