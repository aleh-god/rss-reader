package by.godevelopment.alfarssreader.activities

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
            Log.i(TAG, "MainViewModel: viewModelScope.launch")
            mainSplashRepository
                .getDbIsReadyStateAsFlow()
                .onEach {
                    if (!it) mainSplashRepository.reloadNewsFromRemoteToLocalDataSource()
                    Log.i(TAG, "MainViewModel: .onEach = $it")
                }
                .collect {
                    Log.i(TAG, "MainViewModel: .collect = $it")
                    _isLoading.value = !it
                }
        }
    }
}