package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.lifecycle.ViewModel
import jp.speakbuddy.edisonandroidexercise.data.UserPreferencesRepository
import jp.speakbuddy.edisonandroidexercise.network.FactServiceProvider
import kotlinx.coroutines.runBlocking

class FactViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    fun updateFact(completion: () -> Unit): String =
        runBlocking {
            try {
                val fact = FactServiceProvider.provide().getFact().fact
                userPreferencesRepository.updateLastFact(fact)
                return@runBlocking fact
            } catch (e: Throwable) {
                "something went wrong. error = ${e.message}"
            }.also { completion() }
        }

    fun getFact() = runBlocking {
        userPreferencesRepository.getLastFact()
    }
}
