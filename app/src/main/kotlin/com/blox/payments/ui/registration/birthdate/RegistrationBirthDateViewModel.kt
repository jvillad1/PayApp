package com.blox.payments.ui.registration.birthdate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blox.payments.domain.registration.usecases.ValidateBirthDate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltViewModel
class RegistrationBirthDateViewModel @Inject constructor(
    private val validateBirthDate: ValidateBirthDate
) : ViewModel() {

    private var fetchJob: Job? = null

    var uiState by mutableStateOf(RegistrationBirthDateUiState())
        private set

    var birthDate by mutableStateOf("")
        private set

    fun updateBirthDate(input: String) {
        birthDate = input
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
}
