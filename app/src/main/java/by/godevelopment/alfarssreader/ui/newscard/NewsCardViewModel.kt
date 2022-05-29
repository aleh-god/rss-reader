package by.godevelopment.alfarssreader.ui.newscard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.domain.helpers.StringHelper
import by.godevelopment.alfarssreader.domain.usecases.ChangeFavoriteStatusInNewsCardUseCase
import by.godevelopment.alfarssreader.domain.usecases.GetArticlesAndConvertToCardsItemsUseCase
import by.godevelopment.alfarssreader.domain.models.NewsCardsItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsCardViewModel @Inject constructor(
    private val getArticlesAndConvertToCardsItemsUseCase: GetArticlesAndConvertToCardsItemsUseCase,
    private val changeFavoriteStatusInNewsCardUseCase: ChangeFavoriteStatusInNewsCardUseCase,
    private val stringHelper: StringHelper
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<String>(0)
    val uiEvent: SharedFlow<String> = _uiEvent.asSharedFlow()

    private var fetchJob: Job? = null

    var currentCardPosition: Int? = null

    init {
        fetchDataModel()
    }

    fun fetchDataModel() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            getArticlesAndConvertToCardsItemsUseCase()
                .onStart { _uiState.value = UiState(isFetchingData = true) }
                .catch { exception ->
                    Log.i(TAG, "NewsCardViewModel.catch: ${exception.message}")
                    _uiState.value = UiState(isFetchingData = false)
                    _uiEvent.emit(stringHelper.getString(R.string.alert_error_loading))
                }
                .collect { _uiState.value = UiState(isFetchingData = false, dataList = it) }
        }
    }

    private fun showAlert(message: String) {
        viewModelScope.launch {
            _uiEvent.emit(message)
        }
    }

    fun changeFavoriteStatusInNewsCard(urlKey: String?) {
        urlKey?.let {
            viewModelScope.launch {
                try {
                    changeFavoriteStatusInNewsCardUseCase(urlKey)
                } catch (e: Exception) {
                    Log.i(TAG, "changeFavoriteStatusInNewsCard.catch: ${e.message}")
                    showAlert(stringHelper.getString(R.string.alert_error_IO))
                }
            }
        }
    }

    data class UiState(
        val isFetchingData: Boolean = false,
        val dataList: List<NewsCardsItemModel> = listOf()
    )
}
