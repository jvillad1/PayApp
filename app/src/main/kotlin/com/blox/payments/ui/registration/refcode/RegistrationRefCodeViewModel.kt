package com.blox.payments.ui.registration.refcode

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blox.payments.domain.registration.usecases.WithRefCode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class RegistrationRefCodeViewModel @Inject constructor(
    private val withRefCode: WithRefCode
) : ViewModel() {

    private var fetchJob: Job? = null

    var refCode by mutableStateOf("")
        private set

    fun updateRefCode(input: String) {
        refCode = input
    }

    fun withRefCode() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            Timber.d("Ref code: $refCode")
            withRefCode.invoke(refCode)
        }
    }
}
