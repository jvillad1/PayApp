package com.blox.payments.ui.myscreen.fetch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blox.payments.domain.myscreen.usecases.FetchMyScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FetchViewModel @Inject constructor(
    private val fetchMyScreen: FetchMyScreen
) : ViewModel() {

    private var fetchJob: Job? = null

    var uiState by mutableStateOf(FetchUiState())
        private set

    fun getMyScreen(shouldFail: Boolean) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            fetchMyScreen.invoke(shouldFail)
                .onSuccess { value ->
                    Timber.d("This is a success")
                    uiState = uiState.copy(myScreenIdentifier = value)
                }
                .onFailure { throwable ->
                    Timber.wtf(throwable, "This is a failure")
                    uiState = uiState.copy(noNetwork = true)
                }
        }
    }

    fun navigationHandled() {
        uiState = uiState.copy(
            myScreenIdentifier = null
        )
    }
}
