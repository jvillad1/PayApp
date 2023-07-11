package com.blox.payments.ui.registration.email

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blox.payments.domain.registration.usecases.ValidateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltViewModel
class RegistrationEmailViewModel @Inject constructor(
    private val validateEmail: ValidateEmail
) : ViewModel() {

    private var fetchJob: Job? = null

    var uiState by mutableStateOf(RegistrationEmailState())
        private set

    var email by mutableStateOf("")
        private set

    fun updateEmail(input: String) {
        email = input
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
}
