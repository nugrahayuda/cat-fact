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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import jp.speakbuddy.edisonandroidexercise.R


@Composable
fun FactScreen(
    viewModel: FactViewModel, navController : NavController
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
        val catFactModel by viewModel.catFact.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()

        if (isLoading) {
            AnimatedPreloader()
        } else {
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
                viewModel.fetchCatFact()
            }

            Button(onClick = onClick) {
                Text(text = "Update Fact")
            }

            Button(onClick = { navController.navigate("history_screen") }) {
                Text("View History")
            }
        }
    }
}

@Composable
fun AnimatedPreloader() {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.animation_preloader
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress
    )
}