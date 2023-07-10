package com.blox.payments.ui.registration.country

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
class RegistrationCountryViewModel @Inject constructor(
    private val validateBirthDate: ValidateBirthDate
) : ViewModel() {

    private var fetchJob: Job? = null

    var uiState by mutableStateOf(RegistrationCountryUiState())
        private set

    var country by mutableStateOf("")
        private set

    fun updateCountry(input: String) {
        country = input
    }

    fun validateCountry() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("Country: $country")
            validateBirthDate.invoke(country)
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
}
