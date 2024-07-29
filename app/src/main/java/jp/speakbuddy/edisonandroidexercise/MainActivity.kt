package jp.speakbuddy.edisonandroidexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.speakbuddy.edisonandroidexercise.EdisonAndroidExerciseApplication.Companion.dataStore
import jp.speakbuddy.edisonandroidexercise.data.UserPreferencesRepository
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactScreen
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactViewModel
import jp.speakbuddy.edisonandroidexercise.ui.fact.HistoryScreen
import jp.speakbuddy.edisonandroidexercise.ui.fact.HistoryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreRepository = UserPreferencesRepository(dataStore)
        setContent {
            val navController = rememberNavController()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NavHost(navController = navController, startDestination = "fact_screen") {
                    composable("fact_screen") {
                        FactScreen(viewModel = FactViewModel(dataStoreRepository), navController = navController)
                    }
                    composable("history_screen") {
                        HistoryScreen(viewModel = HistoryViewModel(dataStoreRepository))
                    }
                }
            }
        }
    }
}