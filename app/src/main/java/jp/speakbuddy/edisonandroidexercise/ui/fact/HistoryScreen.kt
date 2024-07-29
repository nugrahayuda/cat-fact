package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp

@Composable
fun HistoryScreen(viewModel: HistoryViewModel) {
    val catFacts by viewModel.factHistory.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(catFacts) { fact ->
            Text(
                text = fact,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}