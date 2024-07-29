package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun FactScreen(
    viewModel: FactViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        var fact by remember { mutableStateOf(viewModel.getLastFact()) }
        val catFactModel by viewModel.catFact.collectAsState()
        Text(
            text = "This is Fact",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = catFactModel?.fact ?: "",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        if (catFactModel?.isMultipleCats == true) {
            Text(
                text = "Multiple cats!",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error
            )
        }

        if ((catFactModel?.length ?: 0) > 100) {
            Text(
                text = "Fact Char " + catFactModel?.length,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error
            )
        }

        val onClick = {
            fact = viewModel.fetchCatFact()
        }

        Button(onClick = onClick) {
            Text(text = "Update Fact")
        }
    }
}
