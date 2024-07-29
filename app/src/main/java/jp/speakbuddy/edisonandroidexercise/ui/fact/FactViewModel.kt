package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.speakbuddy.edisonandroidexercise.data.CatFactRepository
import jp.speakbuddy.edisonandroidexercise.data.UserPreferencesRepository
import jp.speakbuddy.edisonandroidexercise.data.model.FactResponse
import jp.speakbuddy.edisonandroidexercise.network.FactServiceProvider
import jp.speakbuddy.edisonandroidexercise.ui.model.CatFactModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FactViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    private val repository: CatFactRepository = CatFactRepository(FactServiceProvider.provide())
    private val catFactFlow = MutableStateFlow<CatFactModel?>(null)
    val catFact: StateFlow<CatFactModel?> = catFactFlow

    private val isLoadingFlow = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = isLoadingFlow

    init {
        getLastFact()
    }

    fun fetchCatFact() {
        viewModelScope.launch {
            isLoadingFlow.value = true
            repository.getCatFact().collect { fact ->
                catFactFlow.value = handleResponse(fact)
                userPreferencesRepository.updateLastFact(fact.fact)
                userPreferencesRepository.saveCatFact(fact.fact)
            }
            delay(2000) // Add delay to show the animation
            isLoadingFlow.value = false
        }
    }

    fun getLastFact() {
        viewModelScope.launch {
            val fact = userPreferencesRepository.getLastFact()
            catFactFlow.value = CatFactModel(fact, fact.length, isMultipleCats(fact))
        }
    }

    private fun handleResponse(fact: FactResponse): CatFactModel {
        val catFactModel = CatFactModel(
            fact = fact.fact,
            length = fact.length,
            isMultipleCats = isMultipleCats(fact.fact)
        )

        return catFactModel
    }

    private fun isMultipleCats(text: String): Boolean {
        val regex = Regex("cats", RegexOption.IGNORE_CASE)
        val matches = regex.findAll(text)
        return matches.count() > 1
    }
}
