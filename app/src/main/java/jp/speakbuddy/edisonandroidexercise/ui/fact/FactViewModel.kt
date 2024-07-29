package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.speakbuddy.edisonandroidexercise.data.CatFactRepository
import jp.speakbuddy.edisonandroidexercise.data.UserPreferencesRepository
import jp.speakbuddy.edisonandroidexercise.network.FactResponse
import jp.speakbuddy.edisonandroidexercise.network.FactServiceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FactViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    private var catFactModel: CatFactModel? = null
    private val repository: CatFactRepository = CatFactRepository(FactServiceProvider.provide())
    private val catFactFlow = MutableStateFlow<FactResponse?>(null)
    val catFact: StateFlow<FactResponse?> = catFactFlow

    init {
        getLastFact()
    }

    fun getCatFactModel() = catFactModel

    fun fetchCatFact() {
        viewModelScope.launch {
            repository.getCatFact().collect { fact ->
                catFactFlow.value = fact
                userPreferencesRepository.updateLastFact(fact.fact)
            }
        }
    }

    fun getLastFact() {
        viewModelScope.launch {
            val fact = userPreferencesRepository.getLastFact()
            handleResponse(fact)
        }
    }

    private fun handleResponse(fact: String) {
        catFactModel = CatFactModel(
            fact = fact,
            length = fact.length,
            isMultipleCats = isMultipleCats(fact)
        )
    }

    private fun isMultipleCats(text: String): Boolean {
        val regex = Regex("cats", RegexOption.IGNORE_CASE)
        val matches = regex.findAll(text)
        return matches.count() > 1
    }
}

data class CatFactModel(
    val fact: String,
    val length: Int,
    val isMultipleCats: Boolean
)
