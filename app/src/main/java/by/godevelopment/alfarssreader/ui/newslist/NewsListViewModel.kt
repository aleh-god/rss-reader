package by.godevelopment.alfarssreader.ui.newslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.domain.helpers.StringHelper
import by.godevelopment.alfarssreader.domain.usecases.GetArticlesAndConvertToItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getArticlesAndConvertToItemsUseCase: GetArticlesAndConvertToItemsUseCase,
    private val stringHelper: StringHelper
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    private val _uiEvent  = MutableSharedFlow<String>(0)
    val uiEvent: SharedFlow<String> = _uiEvent

    private var fetchJob: Job? = null

    init {
        fetchDataModel()
    }

    fun fetchDataModel() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            getArticlesAndConvertToItemsUseCase()
                .onStart {
                    Log.i(TAG, "NewsListViewModel.launch: .onStart")
                    _uiState.value = UiState(isFetchingData = true)
                }
                .catch { exception ->
                    Log.i(TAG, "NewsListViewModel: .catch ${exception.message}")
                    _uiState.value = UiState(isFetchingData = false)
                    delay(500) // For good UI usability
                    _uiEvent.emit(stringHelper.getString(R.string.alert_error_loading))
                }
                .collect {
                    Log.i(TAG, "NewsListViewModel: .collect = ${it.size}")
                    _uiState.value = UiState(
                        isFetchingData = false,
                        dataList = it
                    )
                }
        }
    }

    fun showAlert(message: String) {
        viewModelScope.launch {
            _uiEvent.emit(message)
        }
    }

    data class UiState(
        val isFetchingData: Boolean = false,
        val dataList: List<NewsItemModel> = listOf()
    )
}