package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.lifecycle.ViewModel
import jp.speakbuddy.edisonandroidexercise.data.UserPreferencesRepository
import jp.speakbuddy.edisonandroidexercise.network.FactServiceProvider
import kotlinx.coroutines.runBlocking

class FactViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    private var catFactModel: CatFactModel? = null

    fun getCatFactModel() = catFactModel

    fun updateFact(completion: () -> Unit): String =
        runBlocking {
            try {
                val fact = FactServiceProvider.provide().getFact().fact
                catFactModel = CatFactModel(
                    fact = fact,
                    length = fact.length,
                    isMultipleCats = isMultipleCats(fact)
                )
                userPreferencesRepository.updateLastFact(fact)
                return@runBlocking fact
            } catch (e: Throwable) {
                "something went wrong. error = ${e.message}"
            }.also { completion() }
        }


    fun getFact() = runBlocking {
        userPreferencesRepository.getLastFact()
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
