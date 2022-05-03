package by.godevelopment.alfarssreader.ui.activities

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.godevelopment.alfarssreader.commons.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainSplashRepository: MainSplashRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            mainSplashRepository
                .getDbIsReadyStateAsFlow()
                .onEach {
                    if (!it) { mainSplashRepository.reloadNewsFromRemoteToLocalDataSource() }
                }
                .catch { throwable ->
                    Log.i(TAG, "MainViewModel: .catch ${throwable.message}")
                }
                .collect { _isLoading.value = !it }
        }
    }
}
