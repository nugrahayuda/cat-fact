package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.speakbuddy.edisonandroidexercise.data.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    private val factHistoryFlow = MutableStateFlow<List<String>>(emptyList())
    val factHistory: StateFlow<List<String>> = factHistoryFlow

    init {
        getFactHistory()
    }

    private fun getFactHistory() {
        viewModelScope.launch {
            val facts = userPreferencesRepository.getHistoryFact()
            factHistoryFlow.value = facts
        }
    }
}