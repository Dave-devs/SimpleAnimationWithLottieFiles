package com.dave_devs.simpleanimationwithlottiefiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dave_devs.simpleanimationwithlottiefiles.ui.theme.SimpleAnimationWithLottieFilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAnimationWithLottieFilesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Animation()
                }
            }
        }
    }
}

@Composable
fun Animation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.chameleon_lottie))
    val progress by animateLottieCompositionAsState(composition = composition, isPlaying = true)
    var isPlaying by remember{ mutableStateOf(true) }

    LaunchedEffect(key1 = progress) {
        if (progress == 1f) isPlaying = true
        if (progress == 0f) isPlaying = false
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            Modifier.size(400.dp)
        )
        Spacer(Modifier.height(8.dp))

        Button(onClick = { isPlaying = true }) {
            Text(text = "Transition")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleAnimationWithLottieFilesTheme {
        Animation()
    }
}