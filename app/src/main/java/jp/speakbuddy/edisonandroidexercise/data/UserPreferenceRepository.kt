package jp.speakbuddy.edisonandroidexercise.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

private const val CAT_FACT_KEY = "cat_fact"

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val LAST_FACT = stringPreferencesKey(CAT_FACT_KEY)
    }

    /**
     * Get last cat fact.
     */
    suspend fun getLastFact(): String {
        val preferences = dataStore.data.first()
        return preferences[PreferencesKeys.LAST_FACT] ?: ""
    }


    suspend fun updateLastFact(lastFact: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_FACT] = lastFact
        }
    }
}