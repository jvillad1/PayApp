package com.blox.payments.ui.registration.password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blox.payments.domain.registration.usecases.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltViewModel
class RegistrationPasswordViewModel @Inject constructor(
    private val validatePassword: ValidatePassword
) : ViewModel() {

    private var fetchJob: Job? = null

    var uiState by mutableStateOf(RegistrationPasswordState())
        private set

    var password by mutableStateOf("")
        private set

    fun updatePassword(input: String) {
        password = input
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
